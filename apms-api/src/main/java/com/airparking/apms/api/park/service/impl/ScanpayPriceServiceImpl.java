package com.airparking.apms.api.park.service.impl;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.park.entity.ScanpayPrice;
import com.airparking.apms.api.park.mapper.ScanpayPriceMapper;
import com.airparking.apms.api.park.service.ScanpayPriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-08-30.
 */
@Service("scanpayPriceService")
public class ScanpayPriceServiceImpl extends AbstractService implements ScanpayPriceService {
    @Autowired
    private ScanpayPriceMapper scanpayPriceMapper;

    @Override
    public void save(ScanpayPrice scanpayPrice) {
      scanpayPriceMapper.add(scanpayPrice);
    }

    @Override
    public void update(ScanpayPrice scanpayPrice) {
      scanpayPriceMapper.update(scanpayPrice);
    }

    @Override
    public ScanpayPrice findByParkId(Long parkId) {
      return scanpayPriceMapper.findByParkId(parkId);
    }

    @Override
    public ScanpayPrice findByParkPriceId(Long priceId) {
      return scanpayPriceMapper.findByParkPriceId(priceId);
    }

}