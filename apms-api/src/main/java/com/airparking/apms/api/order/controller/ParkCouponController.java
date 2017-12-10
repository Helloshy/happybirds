package com.airparking.apms.api.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.order.entity.ParkCoupon;
import com.airparking.apms.api.order.service.ParkCouponService;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;

/**
 * Created by ryan on 2016-09-02.
 */
@Controller("parkCoupon")
public class ParkCouponController extends AbstractController {
  @Autowired
  private ParkCouponService parkCouponService;

  @Authorize(login = false)
  @Path("back")
  public ServiceResponse sendBack(ServiceRequest request) {
    List<ParkCoupon> pcList = null;
    List<Map<String, Object>> backList = null;
    try {
      Map<String, Object> map = null;
      pcList = parkCouponService.findByParkId(request.getLong("parkId"));
      backList = new ArrayList<Map<String, Object>>();
      for (ParkCoupon pc : pcList) {
        map = new HashMap<String, Object>();
        map.put("id", pc.getId());
        map.put("code", pc.getCode());
        map.put("createdBy", pc.getCreatedBy());
        map.put("createdDate", pc.getCreatedDate());
        map.put("discount", pc.getDiscount());
        map.put("effectiveDate", pc.getEffectiveDate());
        map.put("expiredDate", pc.getExpiredDate());
        map.put("limitTime", pc.getLimitTime());
        map.put("mobile", pc.getMobile());
        map.put("plateNo", pc.getPlateNo());
        Byte type = pc.getType();
        map.put("type", type);
        String name = "";
        if (type == 1) {// 1-免费券，2-代金券，4-限价券
          name = pc.getLimitTime() / 60 + "小时免费券";
        } else if (type == 2) {
          name = pc.getDiscount() / 100 + "元代金券";
        } else {
          name = pc.getLimitTime() / 60 + "小时" + pc.getDiscount() / 100 + "元限价券";
        }
        map.put("name", name);
        int status = 0;
        Boolean deleted = pc.getDeleted();
        if (deleted) {
          status = 1;
        } else {
          if (pc.getStatus() != 0) {
            status = 1;
          }
        }
        map.put("status", status);

        backList.add(map);
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(backList);
  }
}