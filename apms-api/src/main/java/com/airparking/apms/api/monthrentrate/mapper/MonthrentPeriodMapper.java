package com.airparking.apms.api.monthrentrate.mapper;

import com.airparking.apms.api.monthrentrate.entity.MonthrentPeriod;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017-05-19.
 */
public interface MonthrentPeriodMapper extends AbstractMapper<MonthrentPeriod, Long> {
	List<MonthrentPeriod> findByMonthrentrateId(@Param("monthrentRateId")Long monthrentRateId);

	MonthrentPeriod findByMonthrentrateIdAndTyp(@Param("monthrentRateId")Long monthrentRateId, @Param("typ")Integer typ);

	int findHoliday();
}
