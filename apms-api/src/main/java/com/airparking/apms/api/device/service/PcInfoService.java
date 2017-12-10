package com.airparking.apms.api.device.service;

import com.airparking.apms.api.device.entity.PcInfo;

/**
 * @create 2017-07-25-12:05
 */

public interface PcInfoService {
	void save(PcInfo pcInfo);

	PcInfo findByDeviceId(Long deviceId);

	void update(PcInfo pcInfo);

	Long findIdByDeviceId(Long id);

	PcInfo findByParkId(Long parkId);
}
