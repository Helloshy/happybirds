package com.airparking.apms.api.parkPrice.service;

import java.util.List;

import com.airparking.apms.api.parkPrice.entity.StepPrice;

/**
 * Created by ryan on 2016-07-28.
 */
public interface StepPriceService {
  void save(StepPrice stepPrice);

  void update(StepPrice stepPrice);

  List<StepPrice> findByPriceId(Long priceId);

  void deleteInTrue(Long id);
}