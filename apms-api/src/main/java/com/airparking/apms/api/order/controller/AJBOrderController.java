package com.airparking.apms.api.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.order.entity.Order;
import com.airparking.apms.api.order.entity.ParkCoupon;
import com.airparking.apms.api.order.service.OrderService;
import com.airparking.apms.api.order.service.ParkCouponService;
import com.airparking.apms.comm.DateFormats;
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
import com.airparking.core.comm.utils.PropertiesUtils;

/**
 * Created by ryan on 2016-07-26.
 */
@Controller("ajbOrder")
public class AJBOrderController extends AbstractController {
  @Autowired
  private OrderService orderService;
  @Autowired
  private ParkCouponService parkCouponService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  public ServiceResponse update(ServiceRequest request) {
    ParkCoupon parkCoupon = null;
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");

    try {
      Map<String, Object> dataMap = JsonUtils.toObject(data, Map.class);
      if (StringUtils.isBlank((String) dataMap.get("cardNo"))
          && StringUtils.isBlank((String) dataMap.get("tradeNo"))) {
        return new ServiceResponse(ResponseCode.MISS_REQUIRED);
      }

      // Order order = JsonUtils.toObject(data, Order.class);

      String endDateStr = StringUtils.isNotBlank((String) dataMap.get("endTime"))
          ? (String) dataMap.get("endTime") : "";
      String startDateStr = StringUtils.isNotBlank((String) dataMap.get("startTime"))
          ? (String) dataMap.get("startTime") : "";
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

      Boolean isAdd = true;
      Order order = new Order();
      order.setDeleted(false);
      order.setParkId(request.getLong("parkId"));
      if (StringUtils.isNotBlank((String) dataMap.get("cardNo"))) {
        if (orderService.getByCardNo((String) dataMap.get("cardNo")) != null) {
          isAdd = false;
          order = orderService.getByCardNo((String) dataMap.get("cardNo"));
        }
      } else {
        if (orderService.getByTradeNo((String) dataMap.get("tradeNo")) != null) {
          isAdd = false;
          order = orderService.getByTradeNo((String) dataMap.get("tradeNo"));
        }
      }

      order.setAdditions(StringUtils.isNotEmpty((String) dataMap.get("additions"))
          ? (String) dataMap.get("additions") : "");
      order.setCardNo(String.valueOf(dataMap.get("cardNo")));
      order.setCode(
          StringUtils.isNotEmpty((String) dataMap.get("code")) ? (String) dataMap.get("code") : "");
      order.setCouponid(StringUtils.isNotEmpty((String) dataMap.get("couponid"))
          ? Long.valueOf((String) dataMap.get("couponid")) : 0l);
      order.setCreatedBy(StringUtils.isNotEmpty((String) dataMap.get("createdBy"))
          ? (String) dataMap.get("createdBy") : "");
      order.setCreatedDate(StringUtils.isNotEmpty((String) dataMap.get("createdDate"))
          ? DateFormats.parseDisplayDatetime((String) dataMap.get("createdDate")) : new Date());
      order.setDeleted(false);
      order.setEndTime(endDate);
      order.setIsSend(true);
      Integer status = Integer.valueOf((String) dataMap.get("orderStatus"));
      order.setOrderStatus(status);
      order.setParkId(parkId);
      order.setPayAmount(StringUtils.isNotEmpty((String) dataMap.get("payAmount"))
          ? (int) ((Double.valueOf((String) dataMap.get("payAmount"))) * 100) : 0);
      order.setPlateNo(StringUtils.isNotEmpty((String) dataMap.get("plateNo"))
          ? (String) dataMap.get("plateNo") : "");
      order.setRateId(StringUtils.isNotEmpty((String) dataMap.get("rateId"))
          ? (String) dataMap.get("rateId") : "");
      order.setRemark(StringUtils.isNotEmpty((String) dataMap.get("remark"))
          ? (String) dataMap.get("remark") : "");
      order.setStartTime(startDate);
      order.setTotalAmount(StringUtils.isNotEmpty((String) dataMap.get("totalAmount"))
          ? (int) ((Double.valueOf((String) dataMap.get("totalAmount"))) * 100) : 0);
      order.setTotalTime(StringUtils.isNotEmpty((String) dataMap.get("totalTime"))
          ? Math.round((Float.valueOf((String) dataMap.get("totalTime"))) * 60) : 0l);
      order.setTradeNo(StringUtils.isNotEmpty((String) dataMap.get("tradeNo"))
          ? (String) dataMap.get("tradeNo") : "");
      order.setType(StringUtils.isNotEmpty((String) dataMap.get("type"))
          ? Byte.valueOf((String) dataMap.get("type")) : 0);
      order.setUpdatedBy(StringUtils.isNotEmpty((String) dataMap.get("updatedBy"))
          ? (String) dataMap.get("updatedBy") : "");
      order.setUpdatedDate(StringUtils.isNotEmpty((String) dataMap.get("updatedDate"))
          ? DateFormats.parseDisplayDatetime((String) dataMap.get("updatedDate")) : new Date());

      if (isAdd) {
        orderService.save(order);
      } else {
        orderService.update(order);
      }

      if (status != Order.OrderStatus.DISCARD.getValue()) {
        Order order1 = orderService.getByTradeNo(order.getTradeNo());
        if (order1 != null && order1.getId() != null && order != null) {
          parkCoupon = parkCouponService.findByCode(order.getCode());
          if (parkCoupon != null) {
            parkCoupon.setOrderId(order1.getId());
            parkCoupon.setStatus((byte) 1);
            parkCouponService.update(parkCoupon);
          }
        }
      }

    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }

  @Authorize(login = false)
  public ServiceResponse onlinePaid(ServiceRequest request) {
    return new ServiceResponse(orderService.findLatestOnlinePaid(request.getLong("parkId"),
        Integer.parseInt(PropertiesUtils.get("online.paid.req.intervals"))));
  }

  @Authorize(login = false)
  @Path("back")
  public ServiceResponse back(ServiceRequest request) {
    Map<String, Object> orderMap = null;
    List<Map<String, Object>> dataList = null;
    try {
      dataList = new ArrayList<>();
      List<Order> orderList = orderService.findByIsSend();
      for (Order o : orderList) {
        if (o.getTradeNo() != null) {
          String code = "";
          orderMap = new HashMap<>();
          orderMap.put("orderId", o.getTradeNo());
          ParkCoupon parkCoupon = parkCouponService.findByOrderId(o.getId());
          if (parkCoupon != null) {
            code = parkCoupon.getCode();
          }
          orderMap.put("coupobCode", code);
          orderMap.put("totalAmount", o.getTotalAmount() == null ? 0 : o.getTotalAmount());
          orderMap.put("payAmount", o.getPayAmount() == null ? 0 : o.getPayAmount());
          orderMap.put("payDate",
              o.getUpdatedDate() == null ? "" : DateHelper.formatDatetime(o.getUpdatedDate()));
          orderMap.put("status", o.getOrderStatus());
          dataList.add(orderMap);

          o.setIsSend(true);
          orderService.update(o);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException(e);
    }

    return new ServiceResponse(dataList);
  }
}