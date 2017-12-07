package com.kapphk.yyt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.yyt.bean.UserMeterRecord;
import com.kapphk.yyt.mapper.UserMeterRecordMapper;
import com.kapphk.yyt.service.MeterRecordService;

/**
 * 订单服务
 * @author Administrator
 *
 */
@Service
public class MeterRecordServiceImpl extends BaseServiceImpl<UserMeterRecord, Long>
		implements MeterRecordService {

	@Autowired
	private UserMeterRecordMapper mapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	@Override
	public PageInfo<Map<String, Object>> findListByUid(String startTime, String endTime, Long uid, Integer page,
			Integer limit){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		PageHelper.orderBy("r.create_time desc");
		PageHelper.startPage(page, limit);
		List<Map<String, Object>> records = mapper.findListByParam(param);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(records);
		return pageInfo;
	}

	@Override
	public Map<String, Object> findMeterRecordById(Long id) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		List<Map<String, Object>> records = mapper.findListByParam(param);
		if(null != records && records.size() == 0){
			return records.get(0);
		}
		return null;
	}



}
