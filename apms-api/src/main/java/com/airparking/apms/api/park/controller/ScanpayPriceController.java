package com.airparking.apms.api.park.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.park.entity.ScanpayPrice;
import com.airparking.apms.api.park.service.ScanpayPriceService;
import com.airparking.apms.api.parkPrice.entity.ParkPrice;
import com.airparking.apms.api.parkPrice.service.ParkPriceService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;

/**
 * Created by ryan on 2016-08-30.
 */
@Controller("scanpayPrice")
public class ScanpayPriceController extends AbstractController {
  @Autowired
  private ScanpayPriceService scanpayPriceService;
  @Autowired
  private ParkPriceService parkPriceService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  @Path("save")
  public ServiceResponse savePrice(ServiceRequest request) {
    String data = request.getString("data");

    try {
      List<Map<String, Object>> sparkList = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> pa : sparkList) {
        ParkPrice parkPrice = parkPriceService.findByRateId((String) pa.get("rateId"));

        if (parkPrice != null) {

          Date now = new Date();
          ScanpayPrice scanpayPrice = scanpayPriceService
              .findByParkId(Long.valueOf((String) pa.get("parkId")));
          if (scanpayPrice != null) {
            scanpayPrice.setPriceId(parkPrice.getId());
            scanpayPrice.setUpdatedDate(now);
            scanpayPriceService.update(scanpayPrice);
          } else {
            scanpayPrice = new ScanpayPrice();
            scanpayPrice.setDeleted(false);
            scanpayPrice.setParkId(Long.valueOf((String) pa.get("parkId")));
            scanpayPrice.setPriceId(parkPrice.getId());
            scanpayPrice.setCreatedDate(now);
            scanpayPriceService.save(scanpayPrice);
          }
        }
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }
}