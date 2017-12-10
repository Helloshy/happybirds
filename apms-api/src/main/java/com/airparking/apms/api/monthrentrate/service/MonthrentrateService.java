package com.airparking.apms.api.monthrentrate.service;

import java.util.Date;
import java.util.List;

import com.airparking.apms.api.monthrentrate.entity.Monthrentrate;

/**
 * Created by ryan on 2016-07-28.
 */
public interface MonthrentrateService {
  Monthrentrate findByMrrId(String mrrId);

  Long findMaxVersion();

  void save(Monthrentrate monthrentrate);

  void update(Monthrentrate monthrentrate);

  List<Monthrentrate> findByThisYearWithParkId(Long parkId, Date startYear, Date endYear);

  Monthrentrate findIsExistByParkIdAndTimeAndName(Long parkId, Date startDate, Date endDate,
      String name);

	List<Monthrentrate> findByUpdatedStatus();

	// Monthrentrate findIsExistByParkIdAndName(Long parkId, String name);
}