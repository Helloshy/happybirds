package com.airparking.apms.api.lane.mapper;

import com.airparking.apms.api.lane.entity.Lane;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface LaneMapper extends AbstractMapper<Lane, Long> {
    List<Lane> findByParkIdAndName(@Param("parkId") Long parkId, @Param("name") String name);

    List<Lane> findByIp(@Param("ip")String ip);
}
