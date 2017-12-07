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
import com.kapphk.entity.EmployeeEntity;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.Role;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.CompanyHallService;
import com.kapphk.system.service.CompanyService;
import com.kapphk.system.service.EmployeeService;
import com.kapphk.system.service.RoleService;
import com.kapphk.system.service.SystemUserService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.bean.Company;
import com.kapphk.yyt.bean.CompanyHall;
/**
 * 员工管理
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController{

    @Autowired
    private EmployeeService  employeeService;
    @Autowired
    private CompanyHallService  companyHallService;
    @Autowired
    private RoleService  roleService;
    @Autowired
    private CompanyService  companyService;
    @Autowired
    private SystemUserService  userService;

    /**
     * 员工信息列表页面
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/company/employee/list";
    }

    /**
     * 获取员工列表
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(SystemUser systemUser,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        	SystemUser user = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
	    	Long compantId = user.getCompanyId();
	    	if(compantId == 1){
	    		compantId = user.getId();
	    	}
	    	systemUser.setCompanyId(compantId);
        	systemUser.setState(1);
        	PageInfo<EmployeeEntity> pageInfo = employeeService.queryEmployeeByPage(systemUser, page, rows, sort);
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
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/addPage",method = RequestMethod.GET)
    public String addPage(Model model){
        try {
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
        	Long companyId = systemUser.getCompanyId();
        	if(systemUser.getCompanyId()==1){
        		companyId = systemUser.getId();
        	}
        	Company company  = companyService.findById(systemUser.getCompanyId());
        	List<CompanyHall> halls = companyHallService.findListByCompanyId(companyId);
        	Role role = new Role();
        	role.setCompanyId(companyId);
        	List<Role> roles = roleService.findAll(role);
        	model.addAttribute("company", company);
        	model.addAttribute("halls", halls);
        	model.addAttribute("roles", roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/employee/add";
    }

    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
        	Long companyId = systemUser.getCompanyId();
        	if(systemUser.getCompanyId()==1){
        		companyId = systemUser.getId();
        	}
        	Company company  = companyService.findById(systemUser.getCompanyId());
        	List<CompanyHall> halls = companyHallService.findListByCompanyId(companyId);
        	Role role = new Role();
        	role.setCompanyId(companyId);
        	List<Role> roles = roleService.findAll(role);
        	systemUser = userService.findById(id);
        	List<Role> userRoles = roleService.findRoleByUserId(systemUser.getId());
        	if(null != userRoles && userRoles.size()>0){
        		model.addAttribute("roleId", userRoles.get(0).getId());
        	}
        	List<CompanyHall> userCompanyHalls  = companyHallService.findListByUsId(systemUser.getId());
        	if(null != userCompanyHalls && userCompanyHalls.size()>0){
        		model.addAttribute("companyHallId", userCompanyHalls.get(0).getId());
        	}
        	model.addAttribute("user", systemUser);
        	model.addAttribute("company", company);
        	model.addAttribute("halls", halls);
        	model.addAttribute("roles", roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/employee/edit";
    }
    
    /**
     * 保存员工信息
     * @param user
     * @param companyHallId
     * @param roleId
     * @return
     */
    @RequestMapping(value="/checkUser",method = RequestMethod.GET)
    public @ResponseBody Result checkUser(String userName){
    	Result result = new Result();
        try {
        	SystemUser user = userService.findByUsername(userName);
        	if(user!= null){
        		result.setStatus(MSG.ERROR.getStatus());
        		result.setMsg("改账号已存在");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(MSG.ERROR.getStatus());
        }
        return result;
    }
    /**
     * 保存员工信息
     * @param user
     * @param companyHallId
     * @param roleId
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(SystemUser user,Long companyHallId,Long roleId){
    	Result result = new Result();
        try {
        	employeeService.saveEmployee(user,companyHallId,roleId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(Contents.ERROR);
        }
        return result;
    }

    /**
     * 批量删除(逻辑删除)
     * @param ids
     * @return
     */
    @SystemLog(description="员工信息-删除")
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Result delete(String ids){
        Result result = new Result();
        try {
        	userService.updateState(ids, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }
}
