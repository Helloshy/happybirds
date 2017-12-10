package com.airparking.apms.api.order.service;

import java.util.List;

import com.airparking.apms.api.order.entity.ParkCoupon;

/**
 * Created by ryan on 2016-09-02.
 */
public interface ParkCouponService {
  ParkCoupon getById(Long id);

  void save(ParkCoupon parkCoupon);

  void update(ParkCoupon parkCoupon);
  
  List<ParkCoupon> findAll();
  
  ParkCoupon findByOrderId(Long orderId);
  
  ParkCoupon findByCode(String code);

  List<ParkCoupon> findByParkId(Long parkId);
}