package com.airparking.apms.api.carModel.service;

import java.util.List;

import com.airparking.apms.api.carModel.entity.CarModel;

/**
 * Created by ryan on 2016-08-05.
 */
public interface CarModelService {
  CarModel findByTypeAndParkId(Byte type, Long parkId);

  void save(CarModel carModel);

  void update(CarModel carModel);

  void delete(Long cmId);
  
  CarModel findById(Long id);
  
  List<CarModel> findAllWithoutCode(Long parkId);
  
  Byte findMaxType(Long parkId);
  
  CarModel findByCode(String code);
  
  CarModel findByIdAndParkId(Long id, Long parkId);
}