package com.airparking.apms.api.parkPrice.service.impl;

import java.util.List;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.parkPrice.entity.ParkPriceExt;
import com.airparking.apms.api.parkPrice.mapper.ParkPriceExtMapper;
import com.airparking.apms.api.parkPrice.service.ParkPriceExtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-07-28.
 */
@Service("parkPriceExtService")
public class ParkPriceExtServiceImpl extends AbstractService implements ParkPriceExtService {
    @Autowired
    private ParkPriceExtMapper parkPriceExtMapper;

    @Override
    public void save(ParkPriceExt parkPriceExt) {
      parkPriceExtMapper.add(parkPriceExt);
    }

    @Override
    public void update(ParkPriceExt parkPriceExt) {
      parkPriceExtMapper.update(parkPriceExt);
    }

    @Override
    public List<ParkPriceExt> findByPriceId(Long priceId) {
      return parkPriceExtMapper.findByPriceId(priceId);
    }

    @Override
    public void deleteInTrue(Long id) {
      parkPriceExtMapper.deleteInTrue(id);
    }

}