package com.airparking.apms.api.account.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.account.entity.OfflineAccount;
import com.airparking.apms.api.account.service.OfflineAccountService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;

/**
 * Created by ryan on 2016-07-27.
 */
@Controller("account")
public class OfflineAccountController extends AbstractController {
  @Autowired
  private OfflineAccountService offlineAccountService;

  @Authorize(login = false)
  public ServiceResponse back(ServiceRequest request) {
    List<Map<String, Object>> dataList = null;
    Long parkId = request.getLong("parkId");

    try {
      Map<String, Object> map = null;
      Date now = new Date();
      Date start = DateUtils.truncate(now, Calendar.DATE);
      Date end = DateUtils.addSeconds(DateUtils.addDays(DateUtils.truncate(now, Calendar.DATE), 1),
          -1);
      List<OfflineAccount> acList = offlineAccountService.findByToday(parkId, start, end);
      dataList = new ArrayList<>();
      for (OfflineAccount ac : acList) {
        map = new HashMap<String, Object>();
        map.put("mobile", ac.getMobile());
        map.put("name", ac.getName());
        map.put("parkId", ac.getParkId());
        map.put("password", ac.getPassword());
        map.put("username", ac.getUsername());
        map.put("status", ac.getDeleted() ? 1 : ac.getIsDisabled() ? 1 : 0);
        dataList.add(map);
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }
    return new ServiceResponse(dataList);
  }

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  public ServiceResponse save(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");

    try {
      OfflineAccount account = null;
      List<Map<String, Object>> acList = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> ac : acList) {
        String username = (String) ac.get("username");
        account = offlineAccountService.findByUsername(username,parkId);
        if (account != null) {
          account.setIsDisabled((Integer) ac.get("status") != 0 ? true : false);
          account.setMobile((String) ac.get("mobile"));
          account.setName((String) ac.get("name"));
          account.setPassword((String) ac.get("password"));
          account.setUpdatedDate(new Date());
          account.setParkId(parkId);

          offlineAccountService.update(account);
        } else {
          account = new OfflineAccount();
          account.setCreatedDate(new Date());
          account.setIsDisabled((Integer) ac.get("status") != 0 ? true : false);
          account.setMobile((String) ac.get("mobile"));
          account.setName((String) ac.get("name"));
          account.setUsername(username);
          account.setPassword((String) ac.get("password"));
          account.setUpdatedDate(new Date());
          account.setParkId(parkId);
          account.setDeleted(false);

          offlineAccountService.save(account);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ServiceResponse(ResponseCode.SERVER_ERROR);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }
}