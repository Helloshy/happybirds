package com.airparking.apms.api.monthrent.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.monthrent.entity.Monthrent;

import java.lang.Long;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ryan on 2016-07-28.
 */
public interface MonthrentMapper extends AbstractMapper<Monthrent, Long> {
  Monthrent findByMrid(@Param("mrid") String mrid);
  Long findMaxVersion();
  List<Monthrent> findByThisYearWithParkId(@Param("parkId") Long parkId,@Param("startYear") Date startYear, @Param("endYear") Date endYear);
}