package com.kapphk.redis.dao.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.kapphk.redis.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient{
	
	@Autowired
	private JedisPool jedisPool; 
	
	// 设置为0的话就是永远都不会过期
    private int expire = 0;
	
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}
	
	
	/**
     * get value from redis
     * 
     * @param key
     * @return
     */
	@Override
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
        	jedis.close();
        }
        return value;
    }
 
    /**
     * set
     * 
     * @param key
     * @param value
     * @return
     */
	@Override
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
        	jedis.close();
        }
        return value;
    }
 
    /**
     * set
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
	@Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
        	jedis.close();
        }
        return value;
    }
 
    /**
     * set
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    @Override
    public String set(String key, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
        	jedis.close();
        }
        return value;
    }
 
    /**
     * del
     * 
     * @param key
     */
    @Override
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
        	jedis.close();
        }
    }

 
    /**
     * flush
     */
    @Override
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
        	jedis.close();
        }
    }
 
    /**
     * size
     */
    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
        	jedis.close();
        }
        return dbSize;
    }
 
    /**
     * keys
     * 
     * @param regex
     * @return
     */
    @Override
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
        	jedis.close();
        }
        return keys;
    }
 
    @Override
    public void dels(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
            Iterator<byte[]> ito = keys.iterator();
            while (ito.hasNext()) {
                jedis.del(ito.next());
            }
        } finally {
        	jedis.close();
        }
    }
 
    public int getExpire() {
        return expire;
    }
 
    public void setExpire(int expire) {
        this.expire = expire;
    }

	@Override
	public Set<byte[]> getKeysPattern(byte[] key) {
		Jedis jedis = jedisPool.getResource();
		Set<byte[]> keys = jedis.keys(key);
		return keys;
	}
}
