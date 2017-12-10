package com.airparking.apms.api.holiday.service.impl;

import com.airparking.apms.api.holiday.entity.Holiday;
import com.airparking.apms.api.holiday.mapper.HolidayMapper;
import com.airparking.apms.api.holiday.service.HolidayService;
import com.airparking.core.base.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
@Service("holidayService")
public class HolidayServiceImpl extends AbstractService implements HolidayService {
    @Autowired
    private HolidayMapper holidayMapper;
    @Override
    public List<Holiday> findAll() {
        return holidayMapper.findAll();
    }

    @Override
    public List<Holiday> findByIsSend(Boolean isSend) {
        return holidayMapper.findByIsSend(isSend);
    }

    @Override
    public void update(Holiday holiday) {
        holidayMapper.update(holiday);
    }
}
