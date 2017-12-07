package com.kapphk.system.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kapphk.anotation.SystemLog;
import com.kapphk.anotation.Token;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.SystemUserService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 用户Controller
 * @author EXIA
 *
 */
@Controller
@RequestMapping("/system/systemUser")
public class SystemUserController extends BaseController {
	@Autowired
	private SystemUserService systemUserService;

	/**
	 * 跳转到用户列表
	 * @return
	 */
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	@Token(remove=true)
	public String systemUser(Model model){
		List<Resource> findRes = findRes();
		model.addAttribute("res", findRes);
		return Common.BACKGROUND_PATH + "/system/systemUser/list";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	
	@RequestMapping(value="/addPage",method=RequestMethod.GET)
	public String addPage(Model model){
		return Common.BACKGROUND_PATH + "/system/systemUser/add";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editPage")
	public String editPage(Model model,Long id){
		try {
			Map<String,Object> map = systemUserService.findUserAndUserRole(id);
			model.addAttribute("sysUser", map.get("sysUser"));
			model.addAttribute("systemUserRole",map.get("systemUserRole"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.BACKGROUND_PATH + "/system/systemUser/edit";
	}
	
	/**
	 * 获取用户列表
	 * @param systemUser
	 * @param page
	 * @param rows
	 * @param sort
	 * @param callback
	 * @return
	 */
	@SystemLog(description="用户管理-查询")
	//@CrossOrigin
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody Object list(SystemUser systemUser,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="id desc")String sort){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			PageInfo<SystemUser> pageInfo = systemUserService.findByPage(systemUser, page, rows, sort);
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
	 * 新增或更新
	 * @param systemUser
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody Object saveData(SystemUser systemUser,Long roleId){
		Result result = new Result();
		try {
			result = systemUserService.saveData(systemUser, result,roleId);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(String ids){
		Result result = new Result();
		try {
			result = systemUserService.delete(ids, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
	
	/**
	 * 修改密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editPwdPage",method=RequestMethod.GET)
	public String editPwdPage(Model model){
		return Common.BACKGROUND_PATH + "/system/systemUser/editPwd";
	}
	
	/**
	 * 修改密码
	 * @param pwd
	 * @param newPwd
	 * @param reNewPwd
	 * @return
	 */
	@RequestMapping(value="/editPwd",method=RequestMethod.POST)
	public @ResponseBody Result editPwd(String pwd, String newPwd, String reNewPwd){
		Result result = new Result();
		try {
			result = systemUserService.editPwd(pwd, newPwd, reNewPwd, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(MSG.ERROR);
		}
		return result;
	}
	
}
