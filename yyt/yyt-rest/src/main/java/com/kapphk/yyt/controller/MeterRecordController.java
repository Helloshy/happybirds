package com.kapphk.yyt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.controller.BaseController;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.service.MeterRecordService;

/**
 * 账单控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/web/meterRecord")
public class MeterRecordController extends BaseController {
	
	@Autowired
	private MeterRecordService recordService;
	
	
	/**
	 * 获取账单列表
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Map<String,Object> list(@RequestParam(required=true)Long uid
			,@RequestParam(required=false)String startTime
			,@RequestParam(required=false)String endTime
			,@RequestParam(required=false,defaultValue="1")Integer page
			,@RequestParam(required=false,defaultValue="10")Integer limit){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			PageInfo<Map<String,Object>> pageInfo = recordService.findListByUid(startTime,endTime,uid,page,limit);
			map.put("data", pageInfo.getList());
			map.put("msg", "返回成功");
			map.put("status",Contents.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",MSG.ERROR.getMsg());
			map.put("status",Contents.ERROR);
		}
		return map;
	}
	
	/**
	 * 账单详情
	 * @return
	 */
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public Map<String,Object> details(@RequestParam(required=true)Long id){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			
			map.put("msg", "返回成功");
			map.put("status",Contents.OK);
			Map<String,Object> order = recordService.findMeterRecordById(id);
			map.put("data", order);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",MSG.ERROR.getMsg());
			map.put("status",Contents.ERROR);
		}
		return map;
	}
	
}
