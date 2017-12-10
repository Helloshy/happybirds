package com.airparking.apms.api.parkPrice.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.airparking.apms.api.carModel.service.CarModelService;
import com.airparking.apms.api.parkPrice.entity.ParkPrice;
import com.airparking.apms.server.ServiceResponse;
import com.alibaba.fastjson.JSONArray;

/**
 * Created by ryan on 2016-07-28.
 */
public interface ParkPriceService {
  ParkPrice findByRateId(String rateId);

  void save(ParkPrice parkPrice);

  void update(ParkPrice parkPrice);

  Long savePriceInLogic(ParkPrice parkPrice, List<Map<String, Object>> epList,
      List<Map<String, Object>> spList) throws ParseException;

  JSONArray priceReturnBack(Date date, Long parkId);

  ParkPrice findByParkIdAndCarModelId(Long parkId, Long carModelId);

  ServiceResponse savePrice(Long priceId, Long parkId, String data,
      ParkPriceService parkPriceService, CarModelService carModelService, String appId) throws Exception;
  
  Integer updateByParkId(Long parkId, Integer retentionTime);
}