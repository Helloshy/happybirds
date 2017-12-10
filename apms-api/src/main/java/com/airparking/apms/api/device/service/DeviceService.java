package com.airparking.apms.api.device.service;

import com.airparking.apms.api.device.entity.Device;

/**
 * Created by spj19931234 on 2017/5/12.
 */
public interface DeviceService {
    void save(Device device);

    Device findByIp(String deviceIp);

    void update(Device device);

	Device findByIpAndLaneId(String ip, Long id,String type);

	Device findByParkIdAndType(Long parkId, int type);

	Long findByDeviceId(String deviceName, Long parkId, String ip);

	Device findById(Long deviceId);

	Device findByParkIdAndIpAndType(Long parkId, String ip, int type);
}
