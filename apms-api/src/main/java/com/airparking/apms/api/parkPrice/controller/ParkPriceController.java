package com.airparking.apms.api.parkPrice.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.carModel.service.CarModelService;
import com.airparking.apms.api.parkPrice.service.ParkPriceService;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.RequiredParams;
import com.alibaba.fastjson.JSONArray;

/**
 * Created by ryan on 2016-07-28.
 */
@Controller("parkPrice")
public class ParkPriceController extends AbstractController {
  @Autowired
  private ParkPriceService parkPriceService;

  @Autowired
  private CarModelService carModelService;

  private static final Logger logger = LoggerFactory.getLogger(ParkPriceController.class);

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  //@Path("save")
  public ServiceResponse savePrice(ServiceRequest request) {
    Long priceId = null;
    String price = request.getString("data");
    Long parkId = request.getLong("parkId");
    ServiceResponse serviceResponse = null;

    try {
      serviceResponse = parkPriceService.savePrice(priceId, parkId, price, parkPriceService,
          carModelService, request.getAppId());
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return serviceResponse;
  }

  @Authorize(login = false)
//  @Path("return")
  public ServiceResponse priceReturnBack(ServiceRequest request) {
    JSONArray priceReturnBack = null;
    Long parkId = request.getLong("parkId");
    try {
      Date now = new Date();
      priceReturnBack = parkPriceService.priceReturnBack(now, parkId);
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(priceReturnBack);
  }
}