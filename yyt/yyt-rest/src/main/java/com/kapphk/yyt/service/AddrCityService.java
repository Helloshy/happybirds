package com.kapphk.yyt.service;

import java.util.List;
import java.util.Map;

import com.kapphk.base.service.BaseService;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.AddrCity;

public interface AddrCityService extends BaseService<AddrCity, String>{


	/**
	 * 获取所有城市并按照首字母排序
	 * @param result
	 * @return
	 */
	List<Map<String,Object>> getAllGroupCode(String cityName);
	
	
}
