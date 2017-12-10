package com.airparking.apms.api.holiday.mapper;

import com.airparking.apms.api.holiday.entity.Holiday;
import com.airparking.core.base.mapper.AbstractMapper;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface HolidayMapper extends AbstractMapper<Holiday, Long> {

    List<Holiday> findAll();
    List<Holiday> findByIsSend(Boolean isSend);
}
