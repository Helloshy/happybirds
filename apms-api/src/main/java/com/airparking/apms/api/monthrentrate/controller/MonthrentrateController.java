package com.airparking.apms.api.monthrentrate.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.airparking.apms.api.monthrentrate.service.MonthrentPeriodService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.monthrentrate.entity.Monthrentrate;
import com.airparking.apms.api.monthrentrate.service.MonthrentrateService;
import com.airparking.apms.comm.HashUtils;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.DateHelper;
import com.airparking.core.comm.utils.JsonUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by ryan on 2016-07-28.
 */
@Controller("monthrentrate")
public class MonthrentrateController extends AbstractController {
  @Autowired
  private MonthrentrateService monthrentrateService;
  @Autowired
  private MonthrentPeriodService monthrentPeriodService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  @Path("save")
  public ServiceResponse add(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");
    String appId = request.getString("appId");

    try {
      List<Map<String, Object>> mrrList = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> mrr : mrrList) {
        Boolean isAdd = true;
        String mrrId = (String) mrr.get("mrrid");// 月保流水号

        Monthrentrate monthrentrate = new Monthrentrate();
        if (monthrentrateService.findByMrrId(mrrId) != null) {
          isAdd = false;
          monthrentrate = monthrentrateService.findByMrrId(mrrId);
        }

        Byte status = Byte.valueOf((String) mrr.get("status"));
        String rateName = (String) mrr.get("name");
        Date startDate = DateHelper.parseDateTime((String) mrr.get("startDate"));
        Date endDate = DateHelper.parseDateTime((String) mrr.get("endDate"));

        monthrentrate.setAdminfee(
            mrr.get("adminFee") == null ? 0 : (int) ((Double) mrr.get("adminFee") * 100));
        monthrentrate.setDeleted(false);
        monthrentrate.setDisabled((byte) 0);
        monthrentrate.setEndDate(endDate);
        monthrentrate.setDeleted(false);
        monthrentrate.setMonthfee(
            mrr.get("monthFee") == null ? 0 : (int) ((Double) mrr.get("monthFee") * 100));
        monthrentrate.setMrrId(mrrId);
        monthrentrate.setName(rateName);
        monthrentrate.setParkId(parkId);
        JSONObject jo = new JSONObject();
        jo.put("mrrId", mrrId);
        jo.put("appId", appId);
        String serialMrrid = HashUtils.md5(jo.toString());
        monthrentrate.setSerialMrrid(serialMrrid);
        monthrentrate.setStartDate(startDate);
        monthrentrate.setStatus(status);
        Date now = new Date();

/*         Monthrentrate rate = null;
        if (status == 1) {
          // 相同名字时间生效的费率
          Date start = DateUtils.truncate(startDate, Calendar.DATE);
          Date end = DateUtils
              .addSeconds(DateUtils.addDays(DateUtils.truncate(endDate, Calendar.DATE), 1), -1);
          rate = monthrentrateService.findIsExistByParkIdAndTimeAndName(parkId, start, end,
              rateName);

          if (rate != null) {
            rate.setDisabled((byte) 1);
            rate.setStatus((byte) 0);
            rate.setUpdatedDate(new Date());
            monthrentrateService.update(rate);
          }
        }*/

        if (isAdd) {
          Long maxVersion = monthrentrateService.findMaxVersion();
          if (maxVersion == null) {
            maxVersion = 0l;
          }
          monthrentrate.setVersion(maxVersion + 1);
          monthrentrate.setCreatedDate(now);
          monthrentrateService.save(monthrentrate);

        } else {
          monthrentrate.setUpdatedDate(now);
          monthrentrateService.update(monthrentrate);
        }
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }

  @Authorize(login = false)
  @Path("return")
  public ServiceResponse returnBack(ServiceRequest request) {
    JSONArray ja = new JSONArray();
    Long parkId = request.getLong("parkId");

    try {
      Date now = new Date();
      Date startYear = DateUtils.truncate(now, Calendar.DATE);
      Date endYear = DateUtils.truncate(DateUtils.addDays(now, 1), Calendar.DATE);
      List<Monthrentrate> mrList = monthrentrateService.findByThisYearWithParkId(parkId, startYear,
          endYear);
      for (Monthrentrate mr : mrList) {
		  Long id = mr.getId();
		  Long periodId = monthrentPeriodService.findByMonthrentrateId(id);
		  if(periodId == null){
			int status = 1;
			JSONObject jo = new JSONObject();
			jo.put("adminFee", mr.getAdminfee());
			jo.put("monthFee", mr.getMonthfee());
			jo.put("mrrid", mr.getMrrId());
			jo.put("name", mr.getName());
			jo.put("parkId", mr.getParkId());
			jo.put("startDate", mr.getStartDate());
			jo.put("endDate", mr.getEndDate());
			if (mr.getDeleted()) {
			  status = 0;
				} else {
			  if (mr.getDisabled() == 1) {
				status = 0;
			  } else {
				if (mr.getStatus() == 0) {
				  status = 0;
				}
			  }
			}
		jo.put("status", status);
		ja.add(jo);
		 }
	  }
    } catch (Exception e) {
      e.printStackTrace();
      return new ServiceResponse(ResponseCode.SERVER_ERROR);
    }

    return new ServiceResponse(ja);
  }
}