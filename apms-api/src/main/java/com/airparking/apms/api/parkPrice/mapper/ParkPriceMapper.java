package com.airparking.apms.api.parkPrice.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airparking.apms.api.parkPrice.entity.ParkPrice;
import com.airparking.core.base.mapper.AbstractMapper;

/**
 * Created by ryan on 2016-07-28.
 */
public interface ParkPriceMapper extends AbstractMapper<ParkPrice, Long> {
  ParkPrice findByRateId(@Param("rateId") String rateId);

  List<ParkPrice> findByDateAndParkId(@Param("startYear") Date startYear,
      @Param("endYear") Date endYear, @Param("parkId") Long parkId);
  
  ParkPrice findByParkIdAndCarModelId(@Param("parkId") Long parkId, @Param("carModelId") Long carModelId);
  
  Integer updateByParkId(@Param("parkId") Long parkId, @Param("retentionTime") Integer retentionTime);
}