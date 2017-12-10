package com.airparking.apms.api.parkPrice.service;

import java.util.List;

import com.airparking.apms.api.parkPrice.entity.ParkPriceExt;

/**
 * Created by ryan on 2016-07-28.
 */
public interface ParkPriceExtService {
  void save(ParkPriceExt parkPriceExt);

  void update(ParkPriceExt parkPriceExt);

  List<ParkPriceExt> findByPriceId(Long priceId);

  void deleteInTrue(Long id);
}