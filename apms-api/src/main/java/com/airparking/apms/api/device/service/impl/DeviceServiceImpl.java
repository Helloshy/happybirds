package com.airparking.apms.api.device.service.impl;

import com.airparking.apms.api.device.entity.Device;
import com.airparking.apms.api.device.mapper.DeviceMapper;
import com.airparking.apms.api.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by spj19931234 on 2017/5/12.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Override
    public void save(Device device) {
        deviceMapper.add(device);
    }

    @Override
    public Device findByIp(String deviceIp) {
        List<Device> list = deviceMapper.findByIp(deviceIp);
        return (list != null && list.size()>0)?list.get(0):null;
    }

    @Override
    public void update(Device device) {
        deviceMapper.update(device);
    }

	@Override
	public Device findByIpAndLaneId(String ip, Long id,String type) {
		return deviceMapper.findByIpAndLaneId(ip,id,type);
	}

	@Override
	public Device findByParkIdAndType(Long parkId, int type) {
		return deviceMapper.findByParkIdAndType(parkId,type);
	}

	@Override
	public Long findByDeviceId(String deviceName, Long parkId, String ip) {
		return deviceMapper.findByDeviceId(deviceName,parkId,ip);
	}

	@Override
	public Device findById(Long deviceId) {
		return deviceMapper.findById(deviceId);
	}

	@Override
	public Device findByParkIdAndIpAndType(Long parkId, String ip, int type) {
		return deviceMapper.findByParkIdAndIpAndType(parkId,ip,type);
	}

}
