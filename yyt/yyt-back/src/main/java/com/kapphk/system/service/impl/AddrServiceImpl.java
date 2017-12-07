package com.kapphk.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.bean.BaseModel;
import com.kapphk.system.service.AddrService;
import com.kapphk.yyt.bean.AddrCity;
import com.kapphk.yyt.bean.AddrDistrict;
import com.kapphk.yyt.bean.AddrProvince;
import com.kapphk.yyt.mapper.AddrCityMapper;
import com.kapphk.yyt.mapper.AddrDistrictMapper;
import com.kapphk.yyt.mapper.AddrProvinceMapper;

/**
 * 员工管理服务层
 * @author Administrator
 *
 */
@Service
public class AddrServiceImpl  implements AddrService {

	@Autowired
	private AddrProvinceMapper mapper;
	@Autowired
	private AddrCityMapper cityMapper;
	@Autowired
	private AddrDistrictMapper districtMapper;

	@Override
	public List getList(String parentId, Integer level) {
		if(level == 1){
			return mapper.findAll(new AddrProvince());
		}else if(level == 2){
			AddrCity city = new AddrCity();
			city.setPid(parentId);
			return cityMapper.findAll(city);
		}else if(level == 3){
			AddrDistrict district = new AddrDistrict();
			district.setCid(parentId);
			return districtMapper.findAll(district);
		}
		return null;
	}
	

}
