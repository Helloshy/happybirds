package com.kapphk.redis.dao;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class JedisDao {
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Resource(name="stringRedisTemplate")
	ValueOperations<String, String> valopsStr;
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	@Resource(name="redisTemplate")
	ValueOperations<Object, Object> valOps;
	
	/**
	 * 保存bean 实体类需实现Serializable接口
	 * @param key
	 * @param entity
	 */
	public <T> void saveEntity(Object key,T entity) {
		valOps.set(key, entity);
	}
	
	/**
	 * 保存bean 需设置生存时间，实体类需实现Serializable接口
	 * @param key
	 * @param entity
	 * @param expire
	 * @param timeUnit
	 */
	public <T> void saveEntity(Object key,T entity,Long expire,TimeUnit timeUnit) {
		valOps.set(key, entity,expire,timeUnit);
	}
	
	/**
	 * 保存字符串
	 * @param key
	 * @param value
	 */
	public void save(String key,String value) {
		valopsStr.set(key, value);
	}
	
	/**
	 * 保存字符串 需设置生存时间
	 * @param key
	 * @param value
	 * @param expire
	 * @param timeUnit
	 */
	public void save(String key,String value,Long expire,TimeUnit timeUnit) {
		valopsStr.set(key, value,expire,timeUnit);
	}
	
	/**
	 * 根据key获取bean
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getEntity(Object key){
		return (T) valOps.get(key);
	}
	
	/**
	 * 根据key获取字符串
	 * @param key
	 * @return
	 */
	public String get(String key){
		return valopsStr.get(key);
	}
	
	public void hashSave(String key,Object hashKey,Object value) {
		HashOperations<String, Object, Object>  hash = stringRedisTemplate.opsForHash();
		hash.put(key, hashKey, value);
	}
	
	public String hashGet(String key,Object hashKey) {
		HashOperations<String, Object, Object>  hash = stringRedisTemplate.opsForHash();
		return hash.get(key, hashKey).toString();
	}
	
	public void hashSaveEntity(String key,Object hashKey,Object value){
		HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
		hash.put(key, hashKey, value);
	}
	
	public Object hashGetEntity(Object key,Object hashKey){
		HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
		return hash.get(key, hashKey);
	}
	
	/**
	 * 删除字符串
	 * @param key
	 */
	public void delete(String key){
		stringRedisTemplate.delete(key);
	}
	
	/**
	 * 删除bean
	 * @param key
	 */
	public void deleteEntity(Object key){
		redisTemplate.delete(key);
	}
}
