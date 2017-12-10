package com.airparking.apms.api.order.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.order.entity.AbnormalProcess;
import com.airparking.apms.api.order.service.AbnormalProcessService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.DateHelper;
import com.airparking.core.comm.utils.JsonUtils;

/**
 * Created by ryan on 2016-07-27.
 */
@Controller("ap")
public class AbnormalProcessController extends AbstractController {
  @Autowired
  private AbnormalProcessService abnormalProcessService;

  @Authorize(login = false)
  @RequiredParams(params = { "data" })
  public ServiceResponse add(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");

    try {
      List<Map<String, Object>> abnormalProcessList = JsonUtils.toObject(data, List.class);
      AbnormalProcess abnormalProcess = null;
      for (Map<String, Object> map : abnormalProcessList) {
        abnormalProcess = new AbnormalProcess();
        abnormalProcess.setDeleted(false);
        abnormalProcess.setParkId(parkId);
        // abnormalProcess.setType(1 << Integer.valueOf((String)
        // map.get("type")));
        abnormalProcess.setType(Integer.valueOf((String) map.get("type")));
        String createdDate = (String) map.get("createdDate");
        if (StringUtils.isNotBlank(createdDate)) {
          abnormalProcess.setCreatedDate(DateHelper.parseDateTime(createdDate));
        }
        abnormalProcess.setCreatedBy((String) map.get("createdBy"));
        abnormalProcess.setOrderTradeNo((String) map.get("orderTradeNo"));
        abnormalProcess.setPlateNo((String) map.get("plateNo"));
        abnormalProcess.setReason((String) map.get("reason"));
        abnormalProcessService.add(abnormalProcess);
      }

    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }
}