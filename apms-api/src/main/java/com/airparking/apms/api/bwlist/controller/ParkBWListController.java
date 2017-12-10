package com.airparking.apms.api.bwlist.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.bwlist.entity.ParkBWList;
import com.airparking.apms.api.bwlist.service.ParkBWListService;
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
 * Created by ryan on 2016-07-26.
 */
@Controller("bwlist")
public class ParkBWListController extends AbstractController {
  @Autowired
  private ParkBWListService parkBWListService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  public ServiceResponse post(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");

    try {
      List<Map<String, Object>> bwlist = JsonUtils.toObject(data, List.class);

      List<ParkBWList> parkBWLists = new ArrayList<>();
      for (Map<String, Object> bw : bwlist) {
        ParkBWList parkBWList = new ParkBWList();
        parkBWList.setMobile((String) bw.get("mobile"));
        parkBWList.setPlateNo((String) bw.get("plateNo"));
        parkBWList.setParkId(parkId);
        parkBWList.setEffectiveDate(DateHelper.parseDateTime((String) bw.get("effectiveDate")));
        parkBWList.setExpiredDate(DateHelper.parseDateTime((String) bw.get("expiredDate")));
        parkBWList.setReason((String) bw.get("reason"));
        Integer status = (Integer) bw.get("status");
        parkBWList.setFlags(status);
        if (status == 0) {
          parkBWList.setDeleted(true);
        } else {
          parkBWList.setDeleted(false);
        }
        parkBWList.setType(((Integer) bw.get("type")));
        parkBWList.setCreatedBy((String) bw.get("createdBy"));
        parkBWList.setCreatedDate(DateHelper.parseDateTime((String) bw.get("createdDate")));
        parkBWList.setUpdatedBy((String) bw.get("updatedBy"));
        parkBWList.setUpdatedDate(DateHelper.parseDateTime((String) bw.get("updatedDate")));

        parkBWLists.add(parkBWList);
      }

      parkBWListService.saveOrUpdate(parkBWLists);
    } catch (IOException | ParseException e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }
}