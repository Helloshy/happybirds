package com.airparking.apms.api.park.service;

import org.apache.ibatis.annotations.Param;

import com.airparking.apms.api.park.entity.ScanpayPrice;

/**
 * Created by ryan on 2016-08-30.
 */
public interface ScanpayPriceService {
  void save(ScanpayPrice scanpayPrice);

  void update(ScanpayPrice scanpayPrice);
  
  ScanpayPrice findByParkId(Long parkId);
  
  ScanpayPrice findByParkPriceId(Long priceId);
}