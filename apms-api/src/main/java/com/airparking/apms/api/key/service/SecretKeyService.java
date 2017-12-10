package com.airparking.apms.api.key.service;

import com.airparking.apms.api.key.entity.SecretKey;

/**
 * Created by ryan on 2016-01-11.
 */
public interface SecretKeyService {
    SecretKey findByAppId(String appId);
}