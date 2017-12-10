package com.airparking.apms.api.monthrentRec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.monthrentRec.entity.MonthrentRec;
import com.airparking.apms.api.monthrentRec.mapper.MonthrentRecMapper;
import com.airparking.apms.api.monthrentRec.service.MonthrentRecService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-07-28.
 */
@Service("monthrentRecService")
public class MonthrentRecServiceImpl extends AbstractService implements MonthrentRecService {
  @Autowired
  private MonthrentRecMapper monthrentRecMapper;

  @Override
  public void save(MonthrentRec monthrentRec) {
    monthrentRecMapper.add(monthrentRec);
  }

  @Override
  public void update(MonthrentRec monthrentRec) {
    monthrentRecMapper.update(monthrentRec);
  }

  @Override
  public MonthrentRec findByserialMrIdAndserialMrrId(String serialMrId, String serialMrrId) {
    return monthrentRecMapper.findByserialMrIdAndserialMrrId(serialMrId, serialMrrId);
  }

  @Override
  public MonthrentRec findBySerialMridAndCreatedDate(String serialMrId, String createdDate) {
    return monthrentRecMapper.findBySerialMridAndCreatedDate(serialMrId, createdDate);
  }

}