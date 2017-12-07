package com.kapphk.yyt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.base.controller.BaseController;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.service.AddrCityService;

/**
 * 城市控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/web/city")
public class AddrCityController extends BaseController {
	
	@Autowired
	private AddrCityService cityService;
	/**
	 * 城市列表
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.GET)
	public Map<String ,Object> list(@RequestParam(required=false,name="q")String cityName){
		Map<String ,Object> result = new HashMap<String ,Object>();
		try {
			result.put("msg", "返回成功");
			result.put("status",Contents.OK);
			List<Map<String ,Object>> data = cityService.getAllGroupCode(cityName);
			result.put("data",data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.put("status",Contents.ERROR);
		}
		return result;
	}
}
