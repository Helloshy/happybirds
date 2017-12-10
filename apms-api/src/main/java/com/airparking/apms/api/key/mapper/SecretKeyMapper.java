package com.airparking.apms.api.key.mapper;

import com.airparking.apms.api.key.entity.SecretKey;

/**
 * Created by ryan on 2016-01-11.
 */
public interface SecretKeyMapper {
    SecretKey findByAppId(String appId);
}