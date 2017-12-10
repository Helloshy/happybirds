package com.airparking.apms.api.park.service.impl;

import java.util.List;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.park.entity.Park;
import com.airparking.apms.api.park.mapper.ParkMapper;
import com.airparking.apms.api.park.service.ParkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-08-01.
 */
@Service("parkService")
public class ParkServiceImpl extends AbstractService implements ParkService {
    @Autowired
    private ParkMapper parkMapper;

    @Override
    public void save(Park park) {
      parkMapper.add(park);
    }

    @Override
    public void update(Park park) {
      parkMapper.update(park);
    }

    @Override
    public Park findById(Long parkId) {
      return parkMapper.get(parkId);
    }

    @Override
    public Park findByName(String name) {
      return parkMapper.findByName(name);
    }

    @Override
    public List<Park> findAll() {
      return parkMapper.findAll();
    }

	@Override
	public List<Park> findAllByDate(String startDate, String endDate) {
		return parkMapper.findAllByDate(startDate,endDate);
	}

}