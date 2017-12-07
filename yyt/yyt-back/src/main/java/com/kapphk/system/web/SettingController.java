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
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.Role;
import com.kapphk.system.bean.Setting;
import com.kapphk.system.bean.SysLog;
import com.kapphk.system.service.SettingService;
import com.kapphk.system.service.SysLogService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;

/** 
*
* @author EXIA
* @version 1.0
* @createDate 2016年9月28日 上午10:38:18 
*
*/
@Controller
@RequestMapping("/system/setting")
public class SettingController extends BaseController {

	@Autowired
	private SettingService settingService;

	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public String sysLog(Model model){
		List<Resource> findRes = findRes();
		model.addAttribute("res", findRes);
		return Common.BACKGROUND_PATH + "/system/setting/list";
	}
	
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value="/addPage",method=RequestMethod.GET)
	public String addPage(Model model){
		return Common.BACKGROUND_PATH + "/system/setting/add";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param model
	 * @return
	 */
	public String editPage(Model model){
		return Common.BACKGROUND_PATH + "/system/setting/edit";
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
	public @ResponseBody Map<String,Object> list(Setting setting,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="id desc")String sort){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			PageInfo<Setting> pageInfo = settingService.findByPage(setting, page, rows, sort);
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
	public @ResponseBody Result saveData(Setting setting){
		Result result = new Result();
		try {
			result = settingService.saveData(setting, result);
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
			result = settingService.delete(ids, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
}



















