package com.airparking.apms.api.parkEarning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.parkEarning.mapper.ParkEarningMapper;
import com.airparking.apms.api.parkEarning.service.ParkEarningService;
import com.airparking.core.base.service.AbstractService;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by shanyong on 2016-12-14.
 */
@Service("parkEarningService")
public class ParkEarningServiceImpl extends AbstractService implements ParkEarningService {
	@Autowired
	private ParkEarningMapper parkEarningMapper;

	@Override
	public List<Map<String, Object>> findMonthrentRecByTenantId(Long tenantId, String start, String end) {
		List<Map<String, Object>> monthrentRecs = parkEarningMapper.findMonthrentRecByTenantId(tenantId, start, end);
		List monthrentRecList = new ArrayList<>();
		for (Map<String, Object> monthrentRec : monthrentRecs) {
			JSONObject jo = new JSONObject();
			jo.put("parkName", monthrentRec.get("parkName"));
			jo.put("plateNo", monthrentRec.get("mainPlateNo"));
			jo.put("payTotal", Double.valueOf(monthrentRec.get("amount")+"")*0.01);
			jo.put("payDate", monthrentRec.get("createdDate"));
			jo.put("startDate", monthrentRec.get("startDate"));
			jo.put("endDate", monthrentRec.get("endDate"));
			monthrentRecList.add(jo);
		}
		return monthrentRecList;
	}

	@Override
	public List findMonthrentParkAndTotal(Long tenantId, String start, String end) {
		List monthrentParksTotalList = new ArrayList<>();
		List<Map<String, Object>> monthrentParksTotal = parkEarningMapper.findMonthrentParkAndTotal(tenantId, start,
				end);
		for (Map<String, Object> monthrentParkTotal : monthrentParksTotal) {
			JSONObject jo = new JSONObject();
			jo.put("parkName", monthrentParkTotal.get("parkName"));
			jo.put("total", Double.valueOf(monthrentParkTotal.get("total")+"")*0.01);
			monthrentParksTotalList.add(jo);
		}
		return monthrentParksTotalList;
	}

	@Override
	public List findParkOrderByTenantId(Long tenantId, String start, String end) {
		List parkOrderList = new ArrayList<>();
		List<Map<String, Object>> parkOrders = parkEarningMapper.findParkOrderByTenantId(tenantId, start, end);
		for (Map<String, Object> parkOrder : parkOrders) {
			JSONObject jo = new JSONObject();
			jo.put("parkName", parkOrder.get("parkName"));
			jo.put("plateNo", parkOrder.get("plateNo"));
			jo.put("payTotal", Double.valueOf(parkOrder.get("amount")+"")*0.01);
			jo.put("payDate", parkOrder.get("updateTime"));
			jo.put("startTime", parkOrder.get("startTime"));
			jo.put("endTime", parkOrder.get("endTime"));
			parkOrderList.add(jo);
		}

		return parkOrderList;
	}

	@Override
	public List findParkOrderAndTotal(Long tenantId, String start, String end) {
		List parkOrdersTotalList = new ArrayList<>();
		List<Map<String, Object>> parkOrdersTotal = parkEarningMapper.findParkOrderAndTotal(tenantId, start, end);
		for (Map<String, Object> parkOrderTotal : parkOrdersTotal) {
			JSONObject jo = new JSONObject();
			jo.put("parkName", parkOrderTotal.get("parkName"));
			jo.put("total", Double.valueOf(parkOrderTotal.get("total")+"")*0.01);

			parkOrdersTotalList.add(jo);
		}
		return parkOrdersTotalList;
	}

	@Override
	public List<Map<String, Object>> findCodePay(String start, String end) {
		return parkEarningMapper.findCodePay(start, end);
	}

	@Override
	public List<Map<String, Object>> findCodePayOrder(String start, String dayend) {
		return parkEarningMapper.findCodePayOrder(start, dayend);
	}

	@Override
	public List<Map<String,Object>> findTenantId() {
		return parkEarningMapper.findTenantId();
	}

	@Override
	public List<String> findTenantMail(Long tenantId){
		return parkEarningMapper.findTenantMail(tenantId);
	}
}