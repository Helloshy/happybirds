package com.airparking.apms.api.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airparking.apms.api.order.entity.ParkCoupon;
import com.airparking.core.base.mapper.AbstractMapper;

/**
 * Created by ryan on 2016-09-02.
 */
public interface ParkCouponMapper extends AbstractMapper<ParkCoupon, Long> {
  List<ParkCoupon> findAll();
  
  ParkCoupon findByOrderId(@Param("orderId") Long orderId);
  
  ParkCoupon findByCode(@Param("code") String code);

  List<ParkCoupon> findByParkId(@Param("parkId") Long parkId);
}