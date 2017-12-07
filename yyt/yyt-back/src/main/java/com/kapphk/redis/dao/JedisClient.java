package com.kapphk.redis.dao;

import java.util.Set;


public interface JedisClient {

	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long incr(String key);
	long expire(String key, int second);
	long ttl(String key);
	long del(String key);
	long hdel(String hkey, String key);
	
	byte[] get(byte[] key);
	byte[] set(byte[] key, byte[] value);
	byte[] set(byte[] key, byte[] value, int expire);
	String set(String key, String value, int expire);
	void del(byte[] key);
	void flushDB();
	Long dbSize();
	Set<byte[]> keys(String pattern);
	void dels(String pattern);
	int getExpire();
	Set<byte[]> getKeysPattern(byte[] key);
	
}
