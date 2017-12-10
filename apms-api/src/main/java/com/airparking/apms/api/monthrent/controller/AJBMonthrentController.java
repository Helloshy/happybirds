package com.airparking.apms.api.monthrent.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.monthrent.entity.Monthrent;
import com.airparking.apms.api.monthrent.service.MonthrentService;
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
import com.alibaba.fastjson.JSONObject;

/**
 * Created by ryan on 2016-07-28.
 */
@Controller("ajbMonthrent")
public class AJBMonthrentController extends AbstractController {
  @Autowired
  private MonthrentService monthrentService;

  @RequiredParams(params = { "data" })
  @Path("save")
  @Authorize(login = false)
  public ServiceResponse change(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");
    String appId = request.getString("appId");

    try {
      Map<String, Object> mr = JsonUtils.toObject(data, Map.class);
      String mrId = (String) mr.get("mrid");// 月保流水号
      // String mrrId = (String) mr.get("mrrid");// 月保费率流水号

      Monthrent monthrent = monthrentService.findByMrid(mrId);
      // Monthrentrate monthrentrate =
      // monthrentrateService.findByMrrId(mrrId);
      Boolean isNew = false;

      if (monthrent == null) {
        isNew = true;
        monthrent = new Monthrent();
        monthrent.setMrid(mrId);
        Long maxVersion = monthrentService.findMaxVersion();
        monthrent.setVersion(maxVersion + 1);
      }

      monthrent.setCarmode((String) mr.get("carmode"));
      byte status = Byte.valueOf((String) mr.get("status"));
      byte disabled = 0;
      if (status == 2) {
        disabled = 1;
      }
      monthrent.setDisabled(disabled);

      String endDateStr = (String) mr.get("endDate");
      String startDateStr = (String) mr.get("startDate");
      Date endDate = null;
      Date startDate = null;
      if (endDateStr.length() == 10 || startDateStr.length() == 10) {

        if (endDateStr.length() == 10) {
          endDate = DateHelper.parse(endDateStr);
        }
        if (startDateStr.length() == 10) {
          startDate = DateHelper.parse(startDateStr);
        }

      } else if (endDateStr.length() == 16 || startDateStr.length() == 16) {

        if (endDateStr.length() == 16) {
          endDate = DateHelper.parseDisplay(endDateStr);
        }
        if (startDateStr.length() == 16) {
          startDate = DateHelper.parseDisplay(startDateStr);
        }

      } else if (endDateStr.length() == 19 || startDateStr.length() == 19) {

        if (endDateStr.length() == 19) {
          endDate = DateHelper.parseDateTime(endDateStr);
        }
        if (startDateStr.length() == 19) {
          startDate = DateHelper.parseDateTime(startDateStr);
        }

      }

      if (startDate == null || endDate == null) {
        return new ServiceResponse(ResponseCode.SERVER_ERROR);
      }

      monthrent.setEndDate(endDate);
      monthrent.setStartDate(startDate);

      monthrent.setDeleted(false);
      monthrent.setMainPlateNo((String) mr.get("mainPlateNo"));
      monthrent.setOwnerMobile((String) mr.get("ownerMobile"));
      monthrent.setOwnerName((String) mr.get("ownerName"));
      monthrent.setParkId(parkId);
      monthrent.setRemark((String) mr.get("remark"));
      JSONObject jo = new JSONObject();
      jo.put("mrId", mrId);
      jo.put("appId", appId);
      String serialMrid = HashUtils.md5(jo.toString());
      monthrent.setSerialMrid(serialMrid);
      // monthrent.setSerialMrrId(monthrentrate.getSerialMrrid());
      monthrent.setSpaceNo((String) mr.get("spaceNo"));
      monthrent.setStatus(status);
      monthrent.setAmount(Integer.valueOf(StringUtils.isNotBlank(String.valueOf(mr.get("amount")))
          ? String.valueOf(mr.get("amount")) : "0"));
      monthrent.setCardNo(String.valueOf(mr.get("cardNo")));
      monthrent.setOtherPlateNo(String.valueOf(mr.get("otherPlateNo")));

      if (isNew) {
        monthrent.setCreatedDate(new Date());
        monthrentService.save(monthrent);
      } else {
        monthrentService.update(monthrent);
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }

  /*
   * @Authorize(login = false)
   * 
   * @Path("return") public ServiceResponse returnBack(ServiceRequest request) {
   * JSONArray ja = new JSONArray(); Long parkId = request.getLong("parkId");
   * 
   * try { Date now = new Date(); Date startYear = DateUtils.truncate(now,
   * Calendar.YEAR); Date endYear = DateUtils.truncate(DateUtils.addYears(now,
   * 1), Calendar.YEAR); List<Monthrent> mrList =
   * monthrentService.findByThisYearWithParkId(parkId, startYear, endYear);
   * JSONObject jo = null; for (Monthrent mr : mrList) { int flags =
   * mr.getStatus();// 0:可用 1：不可用 jo = new JSONObject(); if (flags != 2) { if
   * (mr.getDeleted()) { flags = 2; } else { if (mr.getDisabled() == 1) { flags
   * = 2; } } } jo.put("carmode", mr.getCarmode()); jo.put("mainPlateNo",
   * mr.getMainPlateNo()); jo.put("mrid", mr.getMrid()); jo.put("spaceNo",
   * mr.getSpaceNo()); jo.put("startDate", mr.getStartDate()); jo.put("endDate",
   * mr.getEndDate()); jo.put("status", flags); jo.put("mrrId",
   * mr.getMonthrentrate().getMrrId()); jo.put("cost",
   * mr.getMonthrentrate().getAdminfee() + mr.getMonthrentrate().getMonthfee());
   * ja.add(jo); } } catch (Exception e) { e.printStackTrace(); return new
   * ServiceResponse(ResponseCode.SERVER_ERROR); }
   * 
   * return new ServiceResponse(ja); }
   */

  public static void main(String[] args) {
    String str = "2017-12-30 09:00:00";// 19
    String str1 = "2017-12-30 09:00";// 16
    String str2 = "2017-12-30";// 10
    System.out.println(str.length());
  }
}