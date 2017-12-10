package com.airparking.apms.api.device.mapper;

import com.airparking.apms.api.device.entity.Device;
import com.airparking.apms.api.lane.entity.Lane;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by spj19931234 on 2017/5/12.
 */
public interface DeviceMapper extends AbstractMapper<Device, Long> {
    List<Device> findByIp(@Param("ip")String deviceIp);

	Device findByIpAndLaneId(@Param("ip")String ip, @Param("id")Long LaneId,@Param("type")String type);

	Device findByParkIdAndType(@Param("parkId")Long parkId,@Param("type")int type);

	Long findByDeviceId(@Param("deviceName")String deviceName, @Param("parkId")Long parkId, @Param("ip")String ip);

	Device findById(@Param("deviceId")Long deviceId);

	Device findByParkIdAndIpAndType(@Param("parkId")Long parkId, @Param("ip")String ip, @Param("type")int type);
}
