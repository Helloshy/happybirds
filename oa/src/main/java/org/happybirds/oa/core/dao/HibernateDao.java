package cn.itcast.oa.core.dao;

import java.util.List;

/**
 * HibernateDao
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午4:59:17
 * @version 1.0
 */
public interface HibernateDao {
	/**
	 * 添加的方法
	 * @param entity
	 */
	<T> void save(T entity);
	/**
	 * 查询方法
	 * @param hql
	 * @return
	 */
	<T> List<T> find(String hql);
	
}
