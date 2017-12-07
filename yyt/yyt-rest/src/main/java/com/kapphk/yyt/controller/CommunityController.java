package com.kapphk.yyt.controller;

import java.math.BigDecimal;
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
import com.kapphk.yyt.bean.Community;
import com.kapphk.yyt.service.CommunityService;

/**
 * 小区控制器
 * @author Administrator
 */
@RestController
@RequestMapping("/web/community")
public class CommunityController extends BaseController {
	
	@Autowired
	private CommunityService communityService;
	

	/**
	 * 查询小区列表
	 * @param query
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.GET )
	public Map<String,Object> list(@RequestParam(required=false,name="q")String itemName
			,@RequestParam(defaultValue="1")int page
			,@RequestParam(defaultValue ="10")int limit){
		Map<String ,Object> result = new HashMap<String ,Object>();
		try {
			result.put("msg","返回成功");
			result.put("status",Contents.OK);
			PageInfo<Community> pageInfo = communityService.findPageByItemName(itemName, page, limit);
			result.put("data", pageInfo.getList());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.put("status",Contents.ERROR);
		}
		return result;
	}
	
	/**
	 * 根据经纬度查询附近的小区
	 * @param itemName 小区名
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param page 当前页码
	 * @param limit 每页显示条数
	 * @return
	 */
	@RequestMapping(value = "/nearbyList.htm",method = RequestMethod.GET )
	public Map<String,Object> list1(@RequestParam(required=false,name="q")String itemName
			,@RequestParam(required=false)String city
			,@RequestParam(required=true)BigDecimal longitude
			,@RequestParam(required=true)BigDecimal latitude
			,@RequestParam(defaultValue="1")int page
			,@RequestParam(defaultValue ="10")int limit){
		Map<String ,Object> result = new HashMap<String ,Object>();
		try {
			Community community = new Community();
			community.setItemName(itemName);
			community.setLatitude(latitude);
			community.setLongitude(longitude);
			community.setCity(city);
			result.put("msg","返回成功");
			result.put("status",Contents.OK);
			PageInfo<Community> pageInfo = communityService.nearbyList(community, page, limit);
			result.put("data", pageInfo.getList());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.put("status",Contents.ERROR);
		}
		return result;
	}
}
