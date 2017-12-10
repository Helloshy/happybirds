package com.airparking.apms.api.device.service.impl;

import com.airparking.apms.api.device.entity.PcInfo;
import com.airparking.apms.api.device.mapper.DeviceMapper;
import com.airparking.apms.api.device.mapper.PcInfoMapper;
import com.airparking.apms.api.device.service.PcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @create 2017-07-25-12:06
 */

@Service("pcInfoService")
public class PcInfoServiceImpl implements PcInfoService{

	@Autowired
	private PcInfoMapper pcInfoMapper;

	@Override
	public void save(PcInfo pcInfo) {
		pcInfoMapper.add(pcInfo);
	}

	@Override
	public PcInfo findByDeviceId(Long deviceId) {
		return pcInfoMapper.findByDeviceId(deviceId);
	}

	@Override
	public void update(PcInfo pcInfo) {
		pcInfoMapper.update(pcInfo);
	}

	@Override
	public Long findIdByDeviceId(Long id) {
		return pcInfoMapper.findIdByDeviceId(id);
	}

	@Override
	public PcInfo findByParkId(Long parkId) {
		return pcInfoMapper.findByParkId(parkId);
	}
}
