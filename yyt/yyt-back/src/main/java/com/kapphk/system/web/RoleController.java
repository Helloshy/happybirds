package com.kapphk.system.web;

import java.util.ArrayList;
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
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.Role;
import com.kapphk.system.service.RoleService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
/**
 * 角色Controller
 * @author EXIA
 *
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	
	@Autowired
	public RoleService roleService;
	
	/**
	 * 跳转到角色列表
	 * @return
	 */
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public String Role(Model model){
		List<Resource> findRes = findRes();
		model.addAttribute("res", findRes);
		return Common.BACKGROUND_PATH + "/system/role/list";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value="/addPage",method=RequestMethod.GET)
	public String addPage(Model model){
		return Common.BACKGROUND_PATH + "/system/role/add";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editPage",method=RequestMethod.GET)
	public String editPage(Model model,Long id){
		try {
			Role role = roleService.findById(id);
			model.addAttribute("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.BACKGROUND_PATH + "/system/role/edit";
	}
	
	/**
	 * 获取角色列表
	 * @param role
	 * @param page
	 * @param rows
	 * @param sort
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> list(Role role,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="id desc")String sort){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			PageInfo<Role> pageInfo = roleService.findByPage(role, page, rows, sort);
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
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody Result saveData(Role role){
		Result result = new Result();
		try {
			result = roleService.saveData(role, result);
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
			result = roleService.delete(ids, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
	
	@RequestMapping(value="/selectRole",method=RequestMethod.GET)
	public @ResponseBody List<Role> selectRole(){
		List<Role> list = new ArrayList<Role>();
		try {
			list = roleService.all();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getState() == 0){
					list.remove(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
