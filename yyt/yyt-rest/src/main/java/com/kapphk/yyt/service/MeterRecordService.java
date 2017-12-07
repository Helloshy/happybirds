package com.kapphk.yyt.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseService;
import com.kapphk.yyt.bean.UserMeterRecord;

public interface MeterRecordService extends BaseService<UserMeterRecord, Long> {

	/**
	 * 分页查询订单
	 * @param uid
	 * @param page
	 * @param limit
	 * @return
	 */
	PageInfo<Map<String, Object>> findListByUid(String startTime, String endTime, Long uid, Integer page,
			Integer limit);
	/**
	 * 订单详情
	 * @param orderId 订单编号
	 * @return
	 */
	Map<String, Object> findMeterRecordById(Long id);

	




}
