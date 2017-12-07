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
import com.kapphk.system.service.HelpService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.AppCompanyService;
/**
 * 图文控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/help")
public class HelpController extends BaseController{

    @Autowired
    private HelpService  helpService;

    /**
     * 图文列表页面
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        model.addAttribute("recordType", 1);
        return Common.BACKGROUND_PATH + "/company/help/list";
    }
    /**
     * 帮助中心列表页面
     * @param model
     * @return
     */
    @RequestMapping("/helpListPage")
    public String helpListPage(Model model){
    	List<Resource> findRes = findRes();
    	model.addAttribute("res", findRes);
    	 model.addAttribute("recordType", 2);
    	return Common.BACKGROUND_PATH + "/company/help/list";
    }

    /**
     * 获取图文列表
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="create_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        	PageInfo<AppCompanyService> pageInfo = helpService.findCompanyServiceByPage( page, rows, sort);
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
     * 获取帮助列表
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/helpList",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> helpList(
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="create_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        	PageInfo<AppCompanyService> pageInfo = helpService.findHelpPage( page, rows, sort);
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
    public String addPage(Integer recordType,Model model){
    	model.addAttribute("recordType", recordType);
        return Common.BACKGROUND_PATH + "/company/help/add";
    }

    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	AppCompanyService companyService  = helpService.findById(id);
        	model.addAttribute("companyService", companyService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/help/edit";
    }
    /**
     * 保存图文信息
     * @param community
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(AppCompanyService companyService){
    	Result result = new Result();
        try {
        	 //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
        	companyService.setUpdateTime(new Date());
        	companyService.setUpdateBy(systemUser.getId());
        	if(!ValidateUtils.isBlank(companyService.getId())){
        		helpService.update(companyService);
        	}else{
        		companyService.setCompanyId(systemUser.getCompanyId());
        		
        		companyService.setCreateTime(new Date());
        		helpService.insert(companyService);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(Contents.ERROR);
        }
        return result;
    }

    /**
     * 批量删除图文信息
     * @param ids
     * @return
     */
    @SystemLog(description="图文-删除")
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody  Result delete(String ids){
        Result result = new Result();
        try {
        	helpService.deletes(DataUtils.string2List(ids));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }
}
