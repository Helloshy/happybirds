package com.kapphk.yyt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.yyt.bean.CompanyHall;
import com.kapphk.yyt.mapper.CompanyHallMapper;
import com.kapphk.yyt.service.CompanyHallService;

@Service
public class CompanyHallServiceImpl extends BaseServiceImpl<CompanyHall, Long> implements CompanyHallService{

	@Autowired
	private CompanyHallMapper mapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}


	@Override
	public PageInfo<CompanyHall> nearbyPage(CompanyHall hall, int page, int limit) {
		PageHelper.startPage(page, limit);
		List<CompanyHall> companyHalls = mapper.nearbyPage(hall);
		PageInfo<CompanyHall> pageInfo = new PageInfo<CompanyHall>(companyHalls);
		return pageInfo;
	}
	
}
