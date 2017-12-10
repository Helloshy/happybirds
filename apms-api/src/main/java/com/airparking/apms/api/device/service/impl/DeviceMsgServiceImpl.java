package com.airparking.apms.api.device.service.impl;

import com.airparking.apms.api.device.entity.DeviceMsg;
import com.airparking.apms.api.device.mapper.DeviceMsgMapper;
import com.airparking.apms.api.device.service.DeviceMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @create 2017-07-25-14:22
 */

@Service("deviceMsgService")
public class DeviceMsgServiceImpl implements DeviceMsgService{
	@Autowired
	private DeviceMsgMapper deviceMsgMapper;

	@Override
	public void save(DeviceMsg dmsg) {
		deviceMsgMapper.add(dmsg);
	}
}
