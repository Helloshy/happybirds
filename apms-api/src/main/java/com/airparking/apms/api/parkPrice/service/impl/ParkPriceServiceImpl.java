package com.airparking.apms.api.parkPrice.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airparking.apms.api.carModel.entity.CarModel;
import com.airparking.apms.api.carModel.mapper.CarModelMapper;
import com.airparking.apms.api.carModel.service.CarModelService;
import com.airparking.apms.api.park.entity.Park;
import com.airparking.apms.api.park.entity.ScanpayPrice;
import com.airparking.apms.api.park.mapper.ScanpayPriceMapper;
import com.airparking.apms.api.park.service.ParkService;
import com.airparking.apms.api.parkPrice.entity.ParkPrice;
import com.airparking.apms.api.parkPrice.entity.ParkPriceExt;
import com.airparking.apms.api.parkPrice.entity.StepPrice;
import com.airparking.apms.api.parkPrice.mapper.ParkPriceExtMapper;
import com.airparking.apms.api.parkPrice.mapper.ParkPriceMapper;
import com.airparking.apms.api.parkPrice.mapper.StepPriceMapper;
import com.airparking.apms.api.parkPrice.service.ParkPriceService;
import com.airparking.apms.comm.RocketMQ;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.core.base.service.AbstractService;
import com.airparking.core.comm.utils.DateHelper;
import com.airparking.core.comm.utils.JsonUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

/**
 * Created by ryan on 2016-07-28.
 */
@Service("parkPriceService")
public class ParkPriceServiceImpl extends AbstractService implements ParkPriceService {
  @Autowired
  private ParkPriceMapper parkPriceMapper;

  @Autowired
  private ParkPriceExtMapper parkPriceExtMapper;

  @Autowired
  private StepPriceMapper stepPriceMapper;

  @Autowired
  private CarModelMapper carModelMapper;
  
  @Autowired
  private ScanpayPriceMapper scanpayPriceMapper;
  
  @Autowired
  private ParkService parkService;
  
  private static final String TRADE_TOPIC = "AP_TRADE_TOPIC";
  private static final String TEMPORARY_PARKING_RATE_TAG = "TEMPORARY_PARKING_RATE_TAG";
  private static final String KEY = "TEMPORARY_PARKING_RATE_ID_";

  private static final Logger logger = LoggerFactory.getLogger(ParkPriceService.class);

  @Override
  public ParkPrice findByRateId(String rateId) {
    return parkPriceMapper.findByRateId(rateId);
  }

  @Override
  public void save(ParkPrice parkPrice) {
    parkPriceMapper.add(parkPrice);
  }

  @Override
  public void update(ParkPrice parkPrice) {
    parkPriceMapper.update(parkPrice);
  }

  @Override
  @Transactional(rollbackFor = ParseException.class)
  public Long savePriceInLogic(ParkPrice parkPrice, List<Map<String, Object>> epList,
      List<Map<String, Object>> spList) throws ParseException {
    StepPrice stepPrice = null;
    ParkPriceExt parkPriceExt = null;

    Long priceId = parkPrice.getId();
    boolean isDeleted = false;
    boolean disabled = parkPrice.isDisabled();
    if (disabled) {
      isDeleted = true;
    }
    Date now = new Date();

    List<StepPrice> spLists = stepPriceMapper.findByPriceId(priceId);
    for (StepPrice sprice : spLists) {
      stepPriceMapper.deleteInTrue(sprice.getId());
    }
    List<ParkPriceExt> ppeList = parkPriceExtMapper.findByPriceId(priceId);
    for (ParkPriceExt ppe : ppeList) {
      parkPriceExtMapper.deleteInTrue(ppe.getId());
    }

    for (Map<String, Object> sp : spList) {
      stepPrice = new StepPrice();
      stepPrice.setCreatedDate(now);
      stepPrice.setDeleted(isDeleted);
      stepPrice
          .setFee(sp.get("fee") == null ? 0 : (int) (Double.valueOf((String) sp.get("fee")) * 100));
      stepPrice.setParkTime(
          (double) (sp.get("parkTime") == null ? 0 : Integer.valueOf((String) sp.get("parkTime"))));
      stepPrice.setPriceId(priceId);
      stepPriceMapper.add(stepPrice);
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    for (Map<String, Object> ep : epList) {
      parkPriceExt = new ParkPriceExt();
      parkPriceExt.setCreatedDate(now);
      parkPriceExt.setDeleted(isDeleted);
      if (StringUtils.isNotBlank((String) ep.get("endTime"))) {
        parkPriceExt.setEndTime(simpleDateFormat.parse((String) ep.get("endTime")));
      }
      parkPriceExt.setExtFreeTime((int) (ep.get("extFreeTime") == null ? 0
          : Integer.valueOf((String) ep.get("extFreeTime"))));
      parkPriceExt.setFreeTime(
          (int) (ep.get("freeTime") == null ? 0 : Integer.valueOf((String) ep.get("freeTime"))));
      parkPriceExt.setLimitFee((int) (ep.get("limitFee") == null ? 0
          : Double.valueOf((String) ep.get("limitFee")) * 100));
      int limitTime = 0;
      if (ep.get("limitTime") != null && ep.get("limitTime") != "") {
        limitTime = Integer.valueOf((String) ep.get("limitTime"));
      }
      parkPriceExt.setLimitTime(limitTime);
      parkPriceExt.setPrice(
          (int) (ep.get("price") == null ? 0 : Double.valueOf((String) ep.get("price")) * 100));
      parkPriceExt.setPriceId(priceId);
      parkPriceExt.setPriceUnit(
          (int) (ep.get("priceUnit") == null ? 0 : Double.valueOf((String) ep.get("priceUnit"))));
      if (StringUtils.isNotBlank((String) ep.get("startTime"))) {
        parkPriceExt.setStartTime(simpleDateFormat.parse((String) ep.get("startTime")));
      }
      parkPriceExtMapper.add(parkPriceExt);
    }
    return priceId;
  }

  @Override
  @Transactional
  public JSONArray priceReturnBack(Date date, Long parkId) {
    int status = 0;
    JSONObject parkPriceJson = null;
    JSONArray parkPriceArray = new JSONArray();
    Date startYear = DateUtils.truncate(date, Calendar.YEAR);
    Date endYear = DateUtils.truncate(DateUtils.addYears(date, 1), Calendar.YEAR);
    List<ParkPrice> parkPriceList = parkPriceMapper.findByDateAndParkId(startYear, endYear, parkId);
    for (ParkPrice parkPrice : parkPriceList) {
      parkPriceJson = new JSONObject();
      Long priceId = parkPrice.getId();
      Boolean deleted = parkPrice.getDeleted();
      Boolean disabled = parkPrice.isDisabled();
      Byte flags = parkPrice.getFlags();
      if (deleted) {
        status = 1;
      } else {
        if (disabled) {
          status = 1;
        } else {
          if (flags == 1) {
            status = 1;
          }
        }
      }

      CarModel carModel = carModelMapper.get(parkPrice.getCarModelId());
      parkPriceJson.put("onlineCarModelId", carModel.getId());
      parkPriceJson.put("flags", parkPrice.getFlags() != null ? parkPrice.getFlags() : "");
      parkPriceJson.put("limitFee", parkPrice.getLimitFee() != null ? parkPrice.getLimitFee() : "");
      parkPriceJson.put("limitTime",
          parkPrice.getLimitTime() != null ? parkPrice.getLimitTime() : "");
      parkPriceJson.put("parkId", parkPrice.getParkId() != null ? parkPrice.getParkId() : "");
      parkPriceJson.put("priceType",
          parkPrice.getPriceType() != null ? parkPrice.getPriceType() : "");
      parkPriceJson.put("rateId", parkPrice.getId());
      parkPriceJson.put("localRateId", parkPrice.getRateId());
      parkPriceJson.put("status", status);

      JSONArray parkPriceExtArray = new JSONArray();
      List<ParkPriceExt> parkPriceExtes = parkPriceExtMapper.findByPriceId(priceId);
      for (ParkPriceExt ppe : parkPriceExtes) {
        JSONObject parkPriceExtJson = new JSONObject();
        parkPriceExtJson.put("endTime",
            ppe.getEndTime() != null ? DateHelper.formatDatetime(ppe.getEndTime()) : "");
        parkPriceExtJson.put("extFreeTime",
            ppe.getExtFreeTime() != null ? ppe.getExtFreeTime() : "");
        parkPriceExtJson.put("freeTime", ppe.getFreeTime() != null ? ppe.getFreeTime() : "");
        parkPriceExtJson.put("limitFee", ppe.getLimitFee() != null ? ppe.getLimitFee() : "");
        parkPriceExtJson.put("limitTime", ppe.getLimitTime() != null ? ppe.getLimitTime() : "");
        // parkPriceExtJson.put("maxFee", ppe.getMaxFee());
        parkPriceExtJson.put("price", ppe.getPrice() != null ? ppe.getPrice() : "");
        parkPriceExtJson.put("priceUnit", ppe.getPriceUnit() != null ? ppe.getPriceUnit() : "");
        parkPriceExtJson.put("startTime",
            ppe.getStartTime() != null ? DateHelper.formatDatetime(ppe.getStartTime()) : "");
        parkPriceExtArray.add(parkPriceExtJson);
      }

      JSONArray stepPriceArray = new JSONArray();
      List<StepPrice> StepPrices = stepPriceMapper.findByPriceId(priceId);
      for (StepPrice sp : StepPrices) {
        JSONObject stepPriceJson = new JSONObject();
        stepPriceJson.put("fee", sp.getFee() != null ? String.format("%.2f",Double.valueOf(sp.getFee())/100) : "");
        stepPriceJson.put("parkTime", sp.getParkTime() != null ? sp.getParkTime() : "");
        stepPriceArray.add(stepPriceJson);
      }

      parkPriceJson.put("extPrices", parkPriceExtArray);
      parkPriceJson.put("stepPrices", stepPriceArray);

      parkPrice.setSend(true);
      parkPriceMapper.update(parkPrice);
      parkPriceArray.add(parkPriceJson);
    }
    return parkPriceArray;
  }

  @Override
  public ParkPrice findByParkIdAndCarModelId(Long parkId, Long carModelId) {
    return parkPriceMapper.findByParkIdAndCarModelId(parkId, carModelId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ServiceResponse savePrice(Long priceId, Long parkId, String price,
      ParkPriceService parkPriceService, CarModelService carModelService, String appId) throws Exception {

    if (StringUtils.isBlank(price)) {
      return new ServiceResponse(ResponseCode.MISS_REQUIRED);
    }

    Integer retentionTime = 0;
    Park park = parkService.findById(parkId);
    if(park != null && park.getFreeTime() != null){
      retentionTime = park.getFreeTime();
    }
    List<Map<String, Object>> parkPriceList = JsonUtils.toObject(price, List.class);
    for (Map<String, Object> ppl : parkPriceList) {
      ppl.put("retentionTime", String.valueOf(retentionTime));
      Boolean isAdd = false;
      ParkPrice parkPrice = null;
      Long carModelId = null;
      CarModel carModel = null;
      ScanpayPrice scanpayPrice = null;
      if (ppl.get("onlineCarModelId") != null) {
        carModelId = Long.valueOf((String) ppl.get("onlineCarModelId"));
      } else {
		  logger.info("--------------|| savePrice method carModelId is null ||-------------");
		  continue;
      }
      if (carModelId != null) {
        carModel = carModelService.findByIdAndParkId(carModelId, parkId);
      }

      if (carModel == null) {
        return new ServiceResponse(ResponseCode.MISS_REQUIRED);
      }

      String rateId = (String) ppl.get("rateId");
      parkPrice = parkPriceService.findByRateId(rateId);
      if (parkPrice == null) {
        parkPrice = new ParkPrice();
        parkPrice.setRateId(rateId);
        isAdd = true;
      }
      parkPrice.setCarModelId(carModel.getId());
      parkPrice.setCreatedDate(new Date());
      parkPrice.setDeleted(false);
      parkPrice.setRetentionTime(retentionTime);
      int status = Integer.valueOf((String) ppl.get("status"));
      if (status == 0) {// 生效
        parkPrice.setDisabled(false);
        ParkPrice otherParkPrice = parkPriceService.findByParkIdAndCarModelId(parkId, carModelId);
        if (otherParkPrice != null) {// 查询线上当前是否有生效的临停费率
          otherParkPrice.setDisabled(true);
          parkPriceService.update(otherParkPrice);
          scanpayPrice = scanpayPriceMapper.findByParkPriceId(otherParkPrice.getId());
        }
      } else {
        parkPrice.setDisabled(true);
      }
      parkPrice.setFlags(Byte.valueOf((String) ppl.get("flags")));
      parkPrice.setLimitFee(ppl.get("limitFee") == null ? 0
          : (int) (Double.valueOf((String) ppl.get("limitFee")) * 100));
      parkPrice.setLimitTime(
          ppl.get("limitTime") == null ? 0 : Integer.valueOf((String) ppl.get("limitTime")));
      parkPrice.setParkId(parkId);
      parkPrice.setPriceType(Byte.valueOf((String) ppl.get("priceType")));
      if (isAdd) {
        parkPrice.setSend(true);
        parkPriceService.save(parkPrice);
        if(scanpayPrice != null){
          scanpayPrice.setPriceId(parkPrice.getId());
          scanpayPriceMapper.update(scanpayPrice);
        }
      } else {
        parkPrice.setSend(false);
        parkPriceService.update(parkPrice);
      }

      List<Map<String, Object>> epList = (List<Map<String, Object>>) ppl.get("extPrices");
      List<Map<String, Object>> spList = (List<Map<String, Object>>) ppl.get("stepPrices");
      if (epList.size() <= 0) {
        return new ServiceResponse(ResponseCode.MISS_REQUIRED);
      }
      priceId = parkPriceService.savePriceInLogic(parkPrice, epList, spList);
      
      if(scanpayPriceMapper.findByParkPriceId(parkPrice.getId()) != null){//当前临停费率更新，同步到airparking DataBase
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("data", JsonUtils.toJson(ppl));
        responseData.put("appId", appId);
        SendResult send = new RocketMQ().producer().send(new Message(TRADE_TOPIC, TEMPORARY_PARKING_RATE_TAG, KEY+parkPrice.getId(), JsonUtils.toJson(responseData).getBytes()));
        logger.info(send.getTopic());
      }
    }
    

    return new ServiceResponse(priceId);
  }

@Override
public Integer updateByParkId(Long parkId, Integer retentionTime) {
	return parkPriceMapper.updateByParkId(parkId, retentionTime);
}

}