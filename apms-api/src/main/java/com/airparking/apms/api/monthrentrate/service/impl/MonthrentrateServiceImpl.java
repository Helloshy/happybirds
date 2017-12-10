package com.airparking.apms.api.monthrentrate.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.monthrentrate.entity.Monthrentrate;
import com.airparking.apms.api.monthrentrate.mapper.MonthrentrateMapper;
import com.airparking.apms.api.monthrentrate.service.MonthrentrateService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-07-28.
 */
@Service("monthrentrateService")
public class MonthrentrateServiceImpl extends AbstractService implements MonthrentrateService {
  @Autowired
  private MonthrentrateMapper monthrentrateMapper;

  @Override
  public Monthrentrate findByMrrId(String mrrId) {
    return monthrentrateMapper.findByMrrId(mrrId);
  }

  @Override
  public Long findMaxVersion() {
    return monthrentrateMapper.findMaxVersion();
  }

  @Override
  public void save(Monthrentrate monthrentrate) {
    monthrentrateMapper.add(monthrentrate);
  }

  @Override
  public void update(Monthrentrate monthrentrate) {
    monthrentrateMapper.update(monthrentrate);
  }

  @Override
  public List<Monthrentrate> findByThisYearWithParkId(Long parkId, Date startYear, Date endYear) {
    return monthrentrateMapper.findByThisYearWithParkId(parkId, startYear, endYear);
  }

  @Override
  public Monthrentrate findIsExistByParkIdAndTimeAndName(Long parkId, Date startDate, Date endDate,
      String name) {
    return monthrentrateMapper.findIsExistByParkIdAndTimeAndName(parkId, startDate, endDate, name);
  }

	@Override
	public List<Monthrentrate> findByUpdatedStatus() {
		return monthrentrateMapper.findByUpdatedStatus();
	}

	// @Override
  // public Monthrentrate findIsExistByParkIdAndName(Long parkId, String name) {
  // return monthrentrateMapper.findIsExistByParkIdAndName(parkId, name);
  // }

}