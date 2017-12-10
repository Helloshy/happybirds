package com.airparking.apms.api.monthrent.service;

import java.util.Date;
import java.util.List;

import com.airparking.apms.api.monthrent.entity.Monthrent;

/**
 * Created by ryan on 2016-07-28.
 */
public interface MonthrentService {
  Monthrent findByMrid(String mrid);

  Long findMaxVersion();

  void save(Monthrent monthrent);

  void update(Monthrent monthrent);

  List<Monthrent> findByThisYearWithParkId(Long parkId, Date startYear, Date endYear);
}