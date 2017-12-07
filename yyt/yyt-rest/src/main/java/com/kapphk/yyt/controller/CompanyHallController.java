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
import com.kapphk.yyt.bean.CompanyHall;
import com.kapphk.yyt.service.CompanyHallService;

/**
 * 营业厅控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/web/companyHall")
public class CompanyHallController extends BaseController {
	
	@Autowired
	private CompanyHallService companyHallService;
	
	/**
	 * 根据经纬度查询附近的营业网点
	 * @param itemName 小区名
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param page 当前页码
	 * @param limit 每页显示条数
	 * @return
	 */
	@RequestMapping(value = "/nearbyList.htm",method = RequestMethod.GET )
	public Map<String,Object> list1(@RequestParam(required=true)Long companyId
			,@RequestParam(required=true)BigDecimal longitude
			,@RequestParam(required=true)BigDecimal latitude
			,@RequestParam(defaultValue="1")int page
			,@RequestParam(defaultValue ="10")int limit){
		Map<String ,Object> result = new HashMap<String ,Object>();
		try {
			CompanyHall hall = new CompanyHall();
			hall.setLongitude(longitude);
			hall.setLatitude(latitude);
			hall.setCompanyId(companyId);
			result.put("msg","返回成功");
			result.put("status",Contents.OK);
			PageInfo<CompanyHall> pageInfo = companyHallService.nearbyPage(hall, page, limit);
			result.put("data", pageInfo.getList());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.put("status",Contents.ERROR);
		}
		return result;
	}
}
