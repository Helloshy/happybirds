package com.airparking.apms.comm.aspect;

import com.airparking.cache.annotation.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 1/30/2016.
 */
@Aspect
@Component
public class CacheAspect {
    @Autowired
    @Qualifier("jedisPool")
    private JedisPool jedisPool;

    @Around(value = "@annotation(com.airparking.cache.annotation.Cache) && @annotation(cacheAnno)")
    public Object cache(ProceedingJoinPoint pjp, Cache cacheAnno) {
        String key = cacheAnno.key();

        Jedis jedis = jedisPool.getResource();

        Class clazz = cacheAnno.clazz();
        try {
            Object res = clazz.newInstance();
            //        String val = jedis.get(key);
            if (!jedis.exists(key)) {
                try {
                    res = pjp.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    private void cache() {

    }
}
