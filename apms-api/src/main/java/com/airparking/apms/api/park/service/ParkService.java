package com.airparking.apms.api.park.service;

import java.util.List;

import com.airparking.apms.api.park.entity.Park;

/**
 * Created by ryan on 2016-08-01.
 */
public interface ParkService {
  void save(Park park);

  void update(Park park);

  Park findById(Long parkId);

  Park findByName(String name);

  List<Park> findAll();

	List<Park> findAllByDate(String startDate, String endDate);
}