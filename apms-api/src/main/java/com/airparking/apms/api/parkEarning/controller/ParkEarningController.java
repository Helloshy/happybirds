package com.airparking.apms.api.parkEarning.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.parkEarning.service.ParkEarningService;
import com.airparking.apms.comm.DateFormats;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.comm.utils.DateHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by shanyong on 2016-12-14.
 */
@Controller("parkEarning")
public class ParkEarningController extends AbstractController {
	@Autowired
	private ParkEarningService parkEarningService;

	private static final Logger logger = LoggerFactory.getLogger(ParkEarningController.class);

	@SuppressWarnings("unchecked")
	@Authorize(login = false)
	@Path("return")
	public ServiceResponse returnEarning(ServiceRequest request) {
		JSONArray ja = new JSONArray();

		try {
			Long tenantId = request.getLong("tenantId");
			String startday = request.getString("startday");
			String end = request.getString("endday");
			Date startDate = DateUtils.truncate(DateHelper.parse(startday), Calendar.DAY_OF_MONTH);
			String start = DateHelper.formatDatetime(startDate);
			if(end == null){
				end = DateHelper.format(DateUtils.addDays(startDate, 1));
			}

				if (tenantId == null || startday == null ) {
					logger.info("THE PARAMETER  LOSE, tenantId:{}, startday:{}", tenantId, startday);
					return new ServiceResponse(ResponseCode.MISS_REQUIRED);
				}
				List monthrentRecList = parkEarningService.findMonthrentRecByTenantId(tenantId, start,end);
				List monthrentParksTotalList = parkEarningService.findMonthrentParkAndTotal(tenantId,start, end);
				List parkOrderList = parkEarningService.findParkOrderByTenantId(tenantId, start, end);
				List parkOrdersTotalList = parkEarningService.findParkOrderAndTotal(tenantId, start, end);
				ja.add(monthrentRecList);
				ja.add(monthrentParksTotalList);
				ja.add(parkOrderList);
				ja.add(parkOrdersTotalList);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.warn(e.toString());
			return new ServiceResponse(ResponseCode.SERVER_ERROR);
		}
		return new ServiceResponse(ja);
	}

	@Authorize(login = false)
	@Path("codePay")
	public ServiceResponse returnCodePay(ServiceRequest request) {
		JSONArray ja = new JSONArray();

		try {
			String startday = request.getString("startday");
			Date startDate = DateUtils.truncate(DateHelper.parse(startday), Calendar.DAY_OF_MONTH);
			String start = DateHelper.formatDatetime(startDate);
			String dayend = DateHelper.format(DateUtils.addDays(startDate, 1));
			String monthStart = DateFormats.firstMonthDay(startDate);
			String yearStart = DateFormats.firstYearDay(startDate);
			
			List<Map<String, Object>> dayCodePay = parkEarningService.findCodePay(start,dayend);
				ja.add(listToJoin(dayCodePay));
			List<Map<String, Object>> monthCodePay = parkEarningService.findCodePay(monthStart,dayend);
				ja.add(listToJoin(monthCodePay));
			List<Map<String, Object>> yearCodePay = parkEarningService.findCodePay(yearStart,dayend);
				ja.add(listToJoin(yearCodePay));
			List<Map<String, Object>> allCodePay = parkEarningService.findCodePay(null,null);
				ja.add(listToJoin(allCodePay));
			List<Map<String,Object>> dayCodePayOrder = parkEarningService.findCodePayOrder(start, dayend);
				ja.add(orderListToJoin(dayCodePayOrder));
			
		} catch (ParseException e) {
			e.printStackTrace();
			logger.warn(e.toString());
			return new ServiceResponse(ResponseCode.SERVER_ERROR);
		}
		return new ServiceResponse(ja);
	}

	private List listToJoin(List<Map<String, Object>> codePays) {
		List list = new ArrayList<>();
		for (Map<String, Object> codePay : codePays) {
			JSONObject jo = new JSONObject();
			jo.put("parkName", codePay.get("parkName"));
			jo.put("payCount", codePay.get("payCount"));
			jo.put("paySum", codePay.get("paySum"));
			list.add(jo);
		}
		return list;
	}

	private List orderListToJoin(List<Map<String, Object>> codePays) {
		List list = new ArrayList<>();
		for (Map<String, Object> codePay : codePays) {
			JSONObject jo = new JSONObject();
			jo.put("orderSum", codePay.get("orderSum"));
			jo.put("plateNo", codePay.get("plateNo"));
			jo.put("payTime", codePay.get("payTime"));
			jo.put("startTime", codePay.get("startTime"));
			jo.put("endTime", codePay.get("endTime"));
			jo.put("parkName", codePay.get("parkName"));
			jo.put("paySum", codePay.get("paySum"));
			list.add(jo);
		}
		return list;
	}
	
	@Authorize(login = false)
	@Path("tenant")
	public ServiceResponse returnIdAndMail(ServiceRequest request) {
		List<Map<String,Object>> tenantIdlist = parkEarningService.findTenantId();
		JSONArray ja = new JSONArray();
		for (Map<String,Object> tenant : tenantIdlist) {
			String tenantId = tenant.get("tenantId").toString();
			String tenantName = tenant.get("tenantName").toString();
			String tenantMails = "";
			JSONObject jo = new JSONObject();
			List<String> tenantMaillist = parkEarningService.findTenantMail(Long.valueOf(tenantId));
			if(tenantMaillist != null && tenantMaillist.size()>0){
				for (String tenantMail : tenantMaillist) {
					tenantMails += tenantMail+",";
				}
				jo.put(tenantId+","+tenantName,tenantMails.substring(0, tenantMails.length()-1));
				ja.add(jo);
			}
		}
		return new ServiceResponse(ja);
	}
}