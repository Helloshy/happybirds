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
import com.kapphk.system.service.CompanyPriceService;
import com.kapphk.system.service.CompanyService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.Company;
import com.kapphk.yyt.bean.CompanyPrice;
/**
 * 气价管理控制器
 * @author Administrator
 */
@Controller
@RequestMapping("/companyPrice")
public class CompanyPriceController extends BaseController{
	
	private static final String RECORD_TAG_TYPE = "用户类型";

    @Autowired
    private CompanyPriceService  companyPriceService;
    @Autowired
    private CompanyService  companyService;

    /**
     * 气价列表页面
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/company/price/list";
    }

    /**
     * 获取气价列表
     * @param companyPrice
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(CompanyPrice companyPrice,
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
	    	companyPrice.setCompanyId(companyId);
        	PageInfo<Map<String,Object>> pageInfo = companyPriceService.findPageByParam(companyPrice, page, rows, sort);
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
        	Company company  = companyService.findById(systemUser.getCompanyId());
        	model.addAttribute("company", company);
        } catch (Exception e) {
			e.printStackTrace();
		}
        return Common.BACKGROUND_PATH + "/company/price/add";
    }


    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	CompanyPrice companyPrice  = companyPriceService.findById(id);
        	Company company  = companyService.findById(companyPrice.getCompanyId());
        	model.addAttribute("company", company);
        	model.addAttribute("price", companyPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/price/edit";
    }
    
    /**
     * 保存气价信息
     * @param companyPrice
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(CompanyPrice companyPrice){
    	Result result = new Result();
        try {
        	SystemUser user = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
        	companyPrice.setUpdateBy(user.getId());
        	companyPrice.setUpdateTime(new Date());
        	if(!ValidateUtils.isBlank(companyPrice.getId())){
        		companyPriceService.update(companyPrice);
        	}else{
        		companyPrice.setRecordTagType(RECORD_TAG_TYPE);
        		companyPrice.setCreateTime(new Date());
        		companyPriceService.insert(companyPrice);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg(MSG.ERROR.getMsg());
           
        }
        return result;
    }

    /**
     * 批量删除气价
     * @param ids
     * @return
     */
    @SystemLog(description="气价-删除")
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody  Result delete(String ids){
        Result result = new Result();
        try {
        	companyPriceService.deletes(DataUtils.string2List(ids));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }
}
