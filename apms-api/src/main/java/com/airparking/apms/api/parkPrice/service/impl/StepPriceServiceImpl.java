package com.airparking.apms.api.parkPrice.service.impl;

import java.util.List;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.parkPrice.entity.StepPrice;
import com.airparking.apms.api.parkPrice.mapper.StepPriceMapper;
import com.airparking.apms.api.parkPrice.service.StepPriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-07-28.
 */
@Service("stepPriceService")
public class StepPriceServiceImpl extends AbstractService implements StepPriceService {
    @Autowired
    private StepPriceMapper stepPriceMapper;

    @Override
    public void save(StepPrice stepPrice) {
      stepPriceMapper.add(stepPrice);
    }

    @Override
    public void update(StepPrice stepPrice) {
      stepPriceMapper.update(stepPrice);
    }

    @Override
    public List<StepPrice> findByPriceId(Long priceId) {
      return stepPriceMapper.findByPriceId(priceId);
    }

    @Override
    public void deleteInTrue(Long id) {
      stepPriceMapper.deleteInTrue(id);
    }

}