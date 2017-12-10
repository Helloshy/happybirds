package com.airparking.apms.api.record.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.record.entity.Record;
import com.airparking.apms.api.record.service.RecordService;
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

/**
 * Created by ryan on 2016-07-27.
 */
@Controller("record")
public class RecordController extends AbstractController {
  @Autowired
  private RecordService recordService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  @Path("save")
  public ServiceResponse add(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");

    try {
      List<Map<String, Object>> recordes = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> r : recordes) {
        Record record = new Record();
        record.setApIn(Integer.valueOf((String) r.get("apIn")));
        record.setApOut(Integer.valueOf((String) r.get("apOut")));
        record.setCurrentAp(Integer.valueOf((String) r.get("currentAp")));
        record.setCurrentMonth(Integer.valueOf((String) r.get("currentMonth")));
        record.setCurrentTemp(Integer.valueOf((String) r.get("currentTemp")));
        record.setEndTime(DateHelper.parseDateTime((String) r.get("endTime")));
        record.setMonthIn(Integer.valueOf((String) r.get("monthIn")));
        record.setMonthIncome(r.get("monthIncome") == null ? 0
            : (int) (Double.valueOf((String) r.get("monthIncome")) * 100));
        record.setMonthOut(Integer.valueOf((String) r.get("monthOut")));
        record.setMonthRefund(r.get("monthRefund") == null ? 0
            : (int) (Double.valueOf((String) r.get("monthRefund")) * 100));
        record.setStartTime(DateHelper.parseDateTime((String) r.get("startTime")));
        record.setTempIn(Integer.valueOf((String) r.get("tempIn")));
        record.setTempIncome(r.get("tempIncome") == null ? 0
            : (int) (Double.valueOf((String) r.get("tempIncome")) * 100));
        record.setTempOut(Integer.valueOf((String) r.get("tempOut")));
        record.setUsername((String) r.get("username"));
        record.setParkId(parkId);
        record.setCreatedDate(new Date());
        record.setDeleted(false);
        record.setIsDeleted(false);
        record.setName((String) r.get("name"));
        recordService.save(record);
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }

}