package com.airparking.apms.api.order.service.impl;

import java.util.List;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.order.entity.ParkCoupon;
import com.airparking.apms.api.order.mapper.ParkCouponMapper;
import com.airparking.apms.api.order.service.ParkCouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-09-02.
 */
@Service("parkCouponService")
public class ParkCouponServiceImpl extends AbstractService implements ParkCouponService {
    @Autowired
    private ParkCouponMapper parkCouponMapper;

    @Override
    public ParkCoupon getById(Long id) {
      return parkCouponMapper.get(id);
    }

    @Override
    public void save(ParkCoupon parkCoupon) {
      parkCouponMapper.add(parkCoupon);
    }

    @Override
    public void update(ParkCoupon parkCoupon) {
      parkCouponMapper.update(parkCoupon);
    }

    @Override
    public List<ParkCoupon> findAll() {
      return parkCouponMapper.findAll();
    }

    @Override
    public ParkCoupon findByOrderId(Long orderId) {
      return parkCouponMapper.findByOrderId(orderId);
    }

    @Override
    public ParkCoupon findByCode(String code) {
      return parkCouponMapper.findByCode(code);
    }

	@Override
	public List<ParkCoupon> findByParkId(Long parkId) {
		return parkCouponMapper.findByParkId(parkId);
	}

}