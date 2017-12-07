package com.kapphk.yyt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.AddrCity;
import com.kapphk.yyt.mapper.AddrCityMapper;
import com.kapphk.yyt.service.AddrCityService;

@Service
public class AddrCityServiceImpl extends BaseServiceImpl<AddrCity, String> implements AddrCityService  {

	@Autowired
	private AddrCityMapper mapper;

	@Override
	public void init() {
		super.setMapper(mapper);
		
	}

	@Override
	public List<Map<String,Object>> getAllGroupCode(String cityName) {
		List<AddrCity> citys = mapper.findListByCityName(cityName);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> rs = new HashMap<String,Object>();
		String code = citys.get(0).getCode();
		List<AddrCity> cityRs = new ArrayList<AddrCity>() ;
		for(AddrCity city:citys){
			if(!code.equals(city.getCode())){
				rs.put("code", code);
				rs.put("list", cityRs);
				list.add(rs);
				rs = new HashMap<String,Object>();
				cityRs = new ArrayList<AddrCity>();
			}
			cityRs.add(city);
			code = city.getCode();
		}
		return list;
	}
	
	


	
}
