package com.airparking.apms.api.park.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.park.entity.Park;

import java.lang.Long;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ryan on 2016-08-01.
 */
public interface ParkMapper extends AbstractMapper<Park, Long> {
  Park findByName(@Param("name") String name);

  List<Park> findAll();

	List<Park> findAllByDate(@Param("startDate")String startDate, @Param("endDate")String endDate);
}