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
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.CompanyHallService;
import com.kapphk.system.service.CompanyService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.Company;
import com.kapphk.yyt.bean.CompanyHall;
/**
 * 营业网点控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/companyHall")
public class CompanyHallController extends BaseController{

    @Autowired
    private CompanyHallService  companyHallService;
    @Autowired
    private CompanyService  companyService;

  
    /**
     * 公司信息列表
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/company/hall/list";
    }

    /**
     * 获取营业厅列表
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(CompanyHall companyHall,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
	    	Long companyId = systemUser.getCompanyId();
	    	if(companyId == 1){
	    		companyId = systemUser.getId();
	    	}
	    	companyHall.setCompanyId(companyId);
        	PageInfo<Map<String,Object>> pageInfo = companyHallService.findPageByParam(companyHall, page, rows, sort);
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
	    	model.addAttribute("company", company);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Common.BACKGROUND_PATH + "/company/hall/add";
    }


    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	CompanyHall companyHall  = companyHallService.findById(id);
        	Company company = companyService.findById(companyHall.getCompanyId());
        	model.addAttribute("companyHall", companyHall);
        	model.addAttribute("company", company);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/hall/edit";
    }
    
    /**
     * 保存营业厅信息
     * @param companyHall
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(CompanyHall companyHall){
    	Result result = new Result();
        try {
        	Date date = new Date();
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
    		companyHall.setUpdateBy(systemUser.getId());
    		companyHall.setUpdateTime(date);
    		if(!ValidateUtils.isBlank(companyHall.getId())){
        		companyHallService.update(companyHall);
        	}else{
        		companyHall.setCreateTime(date);
        		companyHallService.insert(companyHall);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(Contents.ERROR);
        }
        return result;
    }

    /**
     * 批量删除营业厅
     * @param ids
     * @return
     */
    @SystemLog(description="营业厅-删除")
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody  Result delete(String ids){
        Result result = new Result();
        try {
            companyHallService.deletes(DataUtils.string2List(ids));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }
}
