package com.airparking.apms.api.monthrentRec.mapper;

import org.apache.ibatis.annotations.Param;

import com.airparking.apms.api.monthrentRec.entity.MonthrentRec;
import com.airparking.core.base.mapper.AbstractMapper;

/**
 * Created by ryan on 2016-07-28.
 */
public interface MonthrentRecMapper extends AbstractMapper<MonthrentRec, Long> {
  MonthrentRec findByserialMrIdAndserialMrrId(@Param("serialMrId") String serialMrId,
      @Param("serialMrrId") String serialMrrId);

  MonthrentRec findBySerialMridAndCreatedDate(@Param("serialMrId") String serialMrId,
      @Param("createdDate") String createdDate);
}