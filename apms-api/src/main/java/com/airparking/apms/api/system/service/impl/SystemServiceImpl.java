package com.airparking.apms.api.system.service.impl;

import com.airparking.apms.api.system.entity.System;
import com.airparking.apms.api.system.mapper.SystemMapper;
import com.airparking.apms.api.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemMapper systemMapper;
    @Override
    public void save(System system) {
        systemMapper.add(system);
    }

    @Override
    public System findByIp(String sysIp) {
        List<System> list = systemMapper.findByIp(sysIp);
        return (list != null && list.size()>0)?list.get(0):null;
    }

    @Override
    public void update(System system) {
        systemMapper.update(system);
    }
}
