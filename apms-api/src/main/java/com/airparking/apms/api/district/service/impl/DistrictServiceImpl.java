package com.airparking.apms.api.district.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.district.mapper.DistrictMapper;
import com.airparking.apms.api.district.service.DistrictService;

/**
 * Created by ryan on 2016-01-28.
 */
@Service("districtService")
public class DistrictServiceImpl extends AbstractService implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;


	@Override
	public List<Map> listAllEnabled(int level) {
		return districtMapper.listAllEnabled(level);
	}
}