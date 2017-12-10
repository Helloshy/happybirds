package com.airparking.apms.api.monthrent.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.monthrent.entity.Monthrent;
import com.airparking.apms.api.monthrent.mapper.MonthrentMapper;
import com.airparking.apms.api.monthrent.service.MonthrentService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-07-28.
 */
@Service("monthrentService")
public class MonthrentServiceImpl extends AbstractService implements MonthrentService {
  @Autowired
  private MonthrentMapper monthrentMapper;

  @Override
  public Monthrent findByMrid(String mrid) {
    return monthrentMapper.findByMrid(mrid);
  }

  @Override
  public Long findMaxVersion() {
    Long maxVersion = 0l;
    if(monthrentMapper.findMaxVersion() != null){
      maxVersion = monthrentMapper.findMaxVersion();
    }
    return maxVersion;
  }

  @Override
  public void save(Monthrent monthrent) {
    monthrentMapper.add(monthrent);
  }

  @Override
  public void update(Monthrent monthrent) {
    monthrentMapper.update(monthrent);
  }

  @Override
  public List<Monthrent> findByThisYearWithParkId(Long parkId, Date startYear, Date endYear) {
    return monthrentMapper.findByThisYearWithParkId(parkId, startYear, endYear);
  }

}