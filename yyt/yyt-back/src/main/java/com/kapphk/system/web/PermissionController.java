package com.kapphk.system.web;

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
import com.kapphk.entity.PermissionEntity;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.Role;
import com.kapphk.system.bean.RoleResource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.bean.SystemUserRole;
import com.kapphk.system.service.EmployeeService;
import com.kapphk.system.service.PermissionService;
import com.kapphk.system.service.RoleService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
/**
 * 权限管理控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController{

    @Autowired
    private PermissionService  permissionService;
    @Autowired
    private EmployeeService  employeeService;
    @Autowired
    private RoleService  roleService;
   
    /**
     * 消息列表页面
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/company/permission/list";
    }

    /**
     * 获取消息列表
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
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
	    	Long companyId = systemUser.getCompanyId();
	    	if(companyId == 1){
	    		companyId = systemUser.getId();
	    	}
	    	Map<String,Object> param = new HashMap<String, Object>();
	    	param.put("companyId", companyId);
        	PageInfo<PermissionEntity> pageInfo = permissionService.findPermissionByPage(param,page, rows, sort);
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
    	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
    	Long companyId = systemUser.getCompanyId();
    	if(systemUser.getCompanyId() == 1){
    		companyId = systemUser.getId();
    	}
    	List<SystemUser> users = employeeService.finByCompanyId(companyId);
    	model.addAttribute("users", users);
    	List<Resource> res = permissionService.getResource();
    	model.addAttribute("resource", res);
        return Common.BACKGROUND_PATH + "/company/permission/add";
    }


    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	Role role = roleService.findById(id);
        	model.addAttribute("role", role);
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
        	Long companyId = systemUser.getCompanyId();
        	if(systemUser.getCompanyId() == 1){
        		companyId = systemUser.getId();
        	}
        	List<SystemUser> users = employeeService.finByCompanyId(companyId);
        	List<SystemUserRole> userRoles = employeeService.finByRoleId(id);
        	for(SystemUser user:users){
        		for(SystemUserRole userRole :userRoles){
        			if(user.getId() == userRole.getUserId()){
        				user.setStatus(1);
        				break;
        			}
        		}
        	}
        	model.addAttribute("users", users);
        	List<Resource> res = permissionService.getResource();
        	model.addAttribute("resource", res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/permission/edit";
    }
    
    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/getResourceById",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getResourceById(Long id){
        Map<String,Object> result = new HashMap<String,Object>();
    	try {
        	List<RoleResource> resources = permissionService.getResourceIdByRoleId(id);
        	result.put("status", MSG.OK.getStatus());
        	result.put("msg", MSG.OK.getMsg());
        	result.put("data", resources);
        } catch (Exception e) {
        	result.put("status", MSG.ERROR.getStatus());
        	result.put("msg", MSG.ERROR.getMsg());
            e.printStackTrace();
        }
    	return result;
    }
    
    
    /**
     * 保存消息信息
     * @param message
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(Long id,String uid,String roleName,String resourceId ){
    	Result result = new Result();
        try {
        	permissionService.save(id,uid,roleName,resourceId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(Contents.ERROR);
        }
        return result;
    }

    /**
     * 批量删除消息
     * @param ids
     * @return
     */
    @SystemLog(description="消息-删除")
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody  Result delete(String ids){
        Result result = new Result();
        try {
        	permissionService.deletes(DataUtils.string2List(ids));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }
}
