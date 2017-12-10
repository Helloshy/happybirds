package com.airparking.apms.api.system.service;

import com.airparking.apms.api.system.entity.System;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface SystemService {
    void save(System system);

    System findByIp(String sysIp);

    void update(System system);
}
