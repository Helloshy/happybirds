package com.kapphk.yyt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.UserNumber;
import com.kapphk.yyt.bean.UserNumberMap;
import com.kapphk.yyt.mapper.UserNumberMapMapper;
import com.kapphk.yyt.mapper.UserNumberMapper;
import com.kapphk.yyt.service.UserNumberService;

@Service
public class UserNumberServiceImpl extends BaseServiceImpl<UserNumber, Long> implements UserNumberService {

	@Autowired
	private UserNumberMapper mapper;
	
	@Autowired
	private UserNumberMapMapper numberMapMapper;

	@Override
	public void init() {
		super.setMapper(mapper);
	}

	@Override
	public Result bindingUser(Long uid, Long unid, Result result){
		UserNumberMap map = new UserNumberMap();
		map.setUid(uid);
		map.setCreateTime(new Date());
		map.setUnid(unid);
		numberMapMapper.insert(map);
		return result;
	}

	@Override
	public PageInfo<UserNumber> queryUnit(String unitName, int page, int limit) {
		PageHelper.startPage(page, limit);
		List<UserNumber> numbers = mapper.queryUnit(unitName);
		PageInfo<UserNumber> pageInfo = new PageInfo<UserNumber>(numbers);
		return pageInfo;
	}

	
	
	@Override
	public List<UserNumber> findListByUid(Long unid, Long uid) {
		
		return mapper.queryByUid(unid,uid);
		
	}

}
