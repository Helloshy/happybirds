package com.kapphk.yyt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.yyt.bean.Community;
import com.kapphk.yyt.mapper.CommunityMapper;
import com.kapphk.yyt.service.CommunityService;

@Service
public class CommunityServiceImpl extends BaseServiceImpl<Community, Long> implements CommunityService{

	@Autowired
	private CommunityMapper mapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}
	
	@Override
	public PageInfo<Community> findPageByItemName(String itemName, int page, int limit) {
		PageHelper.startPage(page, limit);
		List<Community> communities = mapper.findListByItemName(itemName);
		PageInfo<Community> pageInfo = new PageInfo<Community>(communities);
		
		return pageInfo;
	}

	@Override
	public PageInfo<Community> nearbyList(Community community, int page, int limit) {
		PageHelper.startPage(page, limit);
		List<Community> communities = mapper.nearbyList(community);
		PageInfo<Community> pageInfo = new PageInfo<Community>(communities);
		
		return pageInfo;
	}

	
	
}
