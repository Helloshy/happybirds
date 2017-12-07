package com.kapphk.yyt.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseService;
import com.kapphk.yyt.bean.CompanyHall;

public interface CompanyHallService extends BaseService<CompanyHall, Long>{

	/**
	 * 根据经纬度查询附近的小区
	 * @param itemName 小区名
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param page 当前页码
	 * @param limit 每页显示条数
	 * @return
	 */
	PageInfo<CompanyHall> nearbyPage(CompanyHall hall, int page, int limit);
	
	
		
	
	
}
