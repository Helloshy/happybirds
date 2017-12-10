package com.airparking.apms.api.carModel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.carModel.entity.CarModel;
import com.airparking.apms.api.carModel.mapper.CarModelMapper;
import com.airparking.apms.api.carModel.service.CarModelService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-08-05.
 */
@Service("carModelService")
public class CarModelServiceImpl extends AbstractService implements CarModelService {
  @Autowired
  private CarModelMapper carModelMapper;

  @Override
  public CarModel findByTypeAndParkId(Byte type, Long parkId) {
    return carModelMapper.findByTypeAndParkId(type, parkId);
  }

  @Override
  public void save(CarModel carModel) {
    carModelMapper.add(carModel);
  }

  @Override
  public void update(CarModel carModel) {
    carModelMapper.update(carModel);
  }

  @Override
  public void delete(Long cmId) {
    carModelMapper.delete(cmId);
  }

  @Override
  public CarModel findById(Long id) {
    return carModelMapper.get(id);
  }

  @Override
  public List<CarModel> findAllWithoutCode(Long parkId) {
    return carModelMapper.findAllWithoutCode(parkId);
  }

  @Override
  public Byte findMaxType(Long parkId) {
    return carModelMapper.findMaxType(parkId);
  }

  @Override
  public CarModel findByCode(String code) {
    return carModelMapper.findByCode(code);
  }

  @Override
  public CarModel findByIdAndParkId(Long id, Long parkId) {
    return carModelMapper.findByIdAndParkId(id, parkId);
  }

}