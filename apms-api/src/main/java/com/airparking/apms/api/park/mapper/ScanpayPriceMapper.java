package com.airparking.apms.api.park.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.park.entity.ScanpayPrice;

import java.lang.Long;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ryan on 2016-08-30.
 */
public interface ScanpayPriceMapper extends AbstractMapper<ScanpayPrice, Long> {
  ScanpayPrice findByParkId(@Param("parkId") Long parkId);
  ScanpayPrice findByParkPriceId(@Param("priceId") Long priceId);
}