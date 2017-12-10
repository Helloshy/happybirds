package com.airparking.apms.api.system.mapper;

import com.airparking.apms.api.system.entity.System;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface SystemMapper extends AbstractMapper<System, Long> {
    List<System> findByIp(@Param("ip") String ip);
}
