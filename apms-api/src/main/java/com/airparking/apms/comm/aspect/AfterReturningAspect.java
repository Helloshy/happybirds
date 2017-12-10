package com.airparking.apms.comm.aspect;

import com.airparking.cache.annotation.Cache;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Created by ryan on 1/25/16.
 */
@Aspect
@Component
public class AfterReturningAspect {
    @Autowired
    private JedisPool jedisPool;

    @AfterReturning(value = "@annotation(com.airparking.cache.annotation.Cache) && @annotation(cacheAnno)", returning = "retVal")
    public void cache(Cache cacheAnno, Object retVal) {
        String key = cacheAnno.key();
        long expiredTime = cacheAnno.expiredTime();

        if (retVal instanceof Map) {
            Map<String, Object> res = (Map<String, Object>) retVal;
            Jedis jedis = jedisPool.getResource();

            for (Map.Entry<String, Object> entry : res.entrySet()){

            }


        }

        if (expiredTime > 0) {

        } else {
            
        }

    }
}
