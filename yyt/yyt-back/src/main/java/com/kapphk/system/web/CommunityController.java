package com.kapphk.system.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kapphk.anotation.SystemLog;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.entity.CommunityEntity;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.CommunityService;
import com.kapphk.system.service.CompanyHallService;
import com.kapphk.system.service.CompanyService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.Community;
import com.kapphk.yyt.bean.Company;
import com.kapphk.yyt.bean.CompanyHall;
/**
 * 小区控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController{

    @Autowired
    private CommunityService  communityService;
    @Autowired
    private CompanyService  companyService;
    @Autowired
    private CompanyHallService  companyHallService;

    /**
     * 小区信息列表页面
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/company/community/list";
    }

    /**
     * 获取小区列表
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(Community community,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="create_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
	    	Long companyId = systemUser.getCompanyId();
	    	if(companyId == 1){
	    		companyId = systemUser.getId();
	    	}
	    	community.setCompanyId(companyId);
        	PageInfo<CommunityEntity> pageInfo = communityService.findByPageByParam(community, page, rows, sort);
            map.put("rows", pageInfo.getList());
            map.put("page", page);
            map.put("records", pageInfo.getTotal());
            map.put("total", pageInfo.getPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping(value="/addPage",method=RequestMethod.GET)
    public String addPage(Model model){
    	
		try {
			SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
	    	Company company = companyService.findById(systemUser.getCompanyId());
	    	List<CompanyHall> companyHalls =  companyHallService.findListByCompanyId(systemUser.getCompanyId());
	    	model.addAttribute("company", company);
	    	model.addAttribute("companyHalls", companyHalls);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return Common.BACKGROUND_PATH + "/company/community/add";
    }

    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	Community community  = communityService.findById(id);
        	Company company = companyService.findById(community.getCompanyId());
        	List<CompanyHall> companyHalls =  companyHallService.findListByCompanyId(community.getCompanyId());
	    	model.addAttribute("companyHalls", companyHalls);
	    	model.addAttribute("company", company);
        	model.addAttribute("community", community);
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/community/edit";
    }
    
    /**
     * 保存小区信息
     * @param community
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(Community community){
    	Result result = new Result();
        try {
        	if(!ValidateUtils.isBlank(community.getId())){
        		communityService.update(community);
        	}else{
        		community.setCreateTime(new Date());
        		communityService.insert(community);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(Contents.ERROR);
        }
        return result;
    }

    /**
     * 批量删除小区
     * @param ids
     * @return
     */
    @SystemLog(description="小区-删除")
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody  Result delete(String ids){
        Result result = new Result();
        try {
        	communityService.deletes(DataUtils.string2List(ids));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }
    
    /**
     * 根据营业厅获取抄表人员
     * @param hallId
     * @return
     */
    @RequestMapping(value="/getstaff",method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> getstaff(Long hallId){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
        	result.put("status",MSG.OK.getStatus());
        	result.put("msg",MSG.OK.getMsg());
        	List<SystemUser> users = communityService.getstaffByHallId(hallId);
        	result.put("data", users);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status",MSG.ERROR.getStatus());
        	result.put("msg",MSG.ERROR.getMsg());
        }
        return result;
    }
}
