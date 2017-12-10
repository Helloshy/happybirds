package com.airparking.apms.api.parkPrice.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.parkPrice.entity.StepPrice;

import java.lang.Long;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ryan on 2016-07-28.
 */
public interface StepPriceMapper extends AbstractMapper<StepPrice, Long> {
  List<StepPrice> findByPriceId(@Param("priceId") Long priceId);
  void deleteInTrue(@Param("id") Long id);
}