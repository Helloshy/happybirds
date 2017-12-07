package com.kapphk.system.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kapphk.system.service.AddrService;
import com.kapphk.web.utils.Result.MSG;

/**
 * 省市区控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/addr")
public class AddrController {
	
	@Autowired
	private AddrService addrService;
	
	/**
	 * 获取省市区数据
	 * @return
	 */
	@RequestMapping("/list")
	public @ResponseBody Map<String,Object>  list(
			@RequestParam(required=false)String parentId,
			@RequestParam(defaultValue="1")Integer level){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			result.put("status", MSG.OK.getStatus());
			result.put("msg", MSG.OK.getMsg());
			List<?> data =  addrService.getList(parentId, level);
			result.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", MSG.ERROR.getStatus());
			result.put("msg", MSG.ERROR.getMsg());
		}
		
		return result;
	}

}
