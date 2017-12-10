package com.airparking.apms.api.device.mapper;

import com.airparking.apms.api.device.entity.PcInfo;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017-07-25.
 */
public interface PcInfoMapper extends AbstractMapper<PcInfo, Long> {
	PcInfo findByDeviceId(@Param("deviceId")Long deviceId);

	Long findIdByDeviceId(@Param("deviceId")Long deviceId);

	PcInfo findByParkId(@Param("parkId")Long parkId);
}
