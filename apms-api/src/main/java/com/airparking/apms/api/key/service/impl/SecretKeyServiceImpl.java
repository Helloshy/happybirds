package com.airparking.apms.api.key.service.impl;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.key.entity.SecretKey;
import com.airparking.apms.api.key.mapper.SecretKeyMapper;
import com.airparking.apms.api.key.service.SecretKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-01-11.
 */
@Service("secretKeyService")
public class SecretKeyServiceImpl extends AbstractService implements SecretKeyService {
    @Autowired
    private SecretKeyMapper secretKeyMapper;

    public SecretKey findByAppId(String appId) {
        return secretKeyMapper.findByAppId(appId);
    }
}