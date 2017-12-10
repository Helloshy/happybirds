package com.airparking.apms.api.monthrentRec.service;

import com.airparking.apms.api.monthrentRec.entity.MonthrentRec;

/**
 * Created by ryan on 2016-07-28.
 */
public interface MonthrentRecService {
  void save(MonthrentRec monthrentRec);

  void update(MonthrentRec monthrentRec);

  MonthrentRec findByserialMrIdAndserialMrrId(String serialMrId, String serialMrrId);

  MonthrentRec findBySerialMridAndCreatedDate(String serialMrId, String createdDate);
}