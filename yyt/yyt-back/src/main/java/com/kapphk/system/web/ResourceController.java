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

import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kapphk.system.service.ResourceService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
/**
 * 菜单Controller
 * @author EXIA
 *
 */
@Controller
@RequestMapping("/system/resource")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 跳转到角色列表
	 * @return
	 */
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public String Resource(Model model){
		List<Resource> findRes = findRes();
		model.addAttribute("res", findRes);
		return Common.BACKGROUND_PATH + "/system/resource/list";
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
	public String editPage(Model model){
		return Common.BACKGROUND_PATH + "/system/role/edit";
	}
	
	/**
	 * 获取菜单列表
	 * @param role
	 * @param page
	 * @param rows
	 * @param sort
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> list(Resource resource,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="id desc")String sort){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			PageInfo<Resource> pageInfo = resourceService.findByPage(resource, page, rows, sort);
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
	public @ResponseBody Result saveData(Resource resource){
		Result result = new Result();
		try {
			result = resourceService.saveData(resource, result);
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
			result = resourceService.delete(ids, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	@RequestMapping(value="/getAllRes",method=RequestMethod.GET)
	public @ResponseBody List<Resource> getAllRes(){
		List<Resource> list = new ArrayList<Resource>();
		try {
			list = resourceService.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 添加角色权限
	 * @param roleId
	 * @param resourceIds
	 * @return
	 */
	@RequestMapping(value="/addRolePermission",method=RequestMethod.POST)
	public @ResponseBody Result addRolePermission(Long roleId,String resourceIds){
		Result result = new Result();
		try {
			result = resourceService.saveRoleResOfBatch(roleId, resourceIds, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
	
	
	@RequestMapping(value="findResByRoleId",method=RequestMethod.GET)
	public @ResponseBody List<Resource> findResByRoleId(Long roleId){
		return resourceService.findResourceByRoleId(roleId);
	}
	
	@RequestMapping(value="permission",method=RequestMethod.GET)
	public String permissions(Model model) {
		List<Resource> resourceList = new ArrayList<Resource>();
		try {
			resourceList = resourceService.all();
			for (Resource resource : resourceList) {
				resource.setBtn(null);
				resource.setCreateTime(null);
				resource.setIcon(null);
				resource.setPermission(null);
				resource.setRemark(null);
				resource.setName(resource.getResourceName());
				resource.setResourceName(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		model.addAttribute("permission", gson.toJson(resourceList));
		return Common.BACKGROUND_PATH + "/system/resource/permission";
	}
}
