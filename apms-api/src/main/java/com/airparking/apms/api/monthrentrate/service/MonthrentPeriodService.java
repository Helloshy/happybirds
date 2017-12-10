package com.airparking.apms.api.monthrentrate.service;

import com.airparking.apms.api.monthrentrate.entity.MonthrentPeriod;
import com.airparking.apms.server.ServiceResponse;
import com.alibaba.fastjson.JSONArray;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2017-05-19-19:18
 */

public interface MonthrentPeriodService {
	ServiceResponse createPeriod(Map<String, Object> mrr, Long parkId, String appId) throws ParseException;

	JSONArray returnPeriod(JSONArray ja);

	Long findByMonthrentrateId(Long id);
}
