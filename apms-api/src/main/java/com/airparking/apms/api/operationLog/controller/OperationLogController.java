package com.airparking.apms.api.operationLog.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.operationLog.entity.OperationLog;
import com.airparking.apms.api.operationLog.service.OperationLogService;
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
 * Created by ryan on 2016-08-03.
 */
@Controller("OperationLog")
public class OperationLogController extends AbstractController {
  @Autowired
  private OperationLogService operationLogService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  @Path("save")
  public ServiceResponse savePrice(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");

    try {
      if (StringUtils.isBlank(data)) {
        return new ServiceResponse(ResponseCode.MISS_REQUIRED);
      }

      OperationLog operationLog = null;
      List<Map<String, Object>> operationLogList = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> opl : operationLogList) {
        operationLog = new OperationLog();
        operationLog.setCreatedDate(new Date());
        operationLog.setDeleted(false);
        operationLog.setIsDeleted(false);
        operationLog.setModule((String) opl.get("module"));
        operationLog.setOperation((String) opl.get("operation"));
        operationLog.setParkId(parkId);
        operationLog.setType((String) opl.get("type"));
        String createdDate = (String) opl.get("createdDate");
        if(StringUtils.isNotBlank(createdDate)){
          operationLog.setCreatedDate(DateHelper.parseDateTime(createdDate));
        }
        operationLog.setCreatedBy((String) opl.get("createdBy"));
        operationLogService.save(operationLog);
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }
}