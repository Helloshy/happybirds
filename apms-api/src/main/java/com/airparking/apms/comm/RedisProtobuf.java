package com.airparking.apms.comm;

import com.airparking.cache.redis.AbstractRedisService;
import com.google.protobuf.AbstractMessage;

/**
 * Created by ryan on 1/13/16.
 */
abstract public class RedisProtobuf<T> extends AbstractRedisService<AbstractMessage> {

    @Override
    public void setex(String key, int seconds, AbstractMessage value) throws Exception {
        setex(key.getBytes("UTF-8"), seconds, value.toByteArray());
    }

    @Override
    public void set(String key, AbstractMessage value) throws Exception {
        set(key.getBytes("UTF-8"), value.toByteArray());
    }

    abstract public T get(String key) throws Exception;
}
