package com.airparking.apms.api.holiday.service;

import com.airparking.apms.api.holiday.entity.Holiday;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface HolidayService {
    List<Holiday> findAll();
    List<Holiday> findByIsSend(Boolean isSend);
    void update(Holiday holiday);
}
