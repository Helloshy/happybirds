package cn.itcast.oa.core.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import cn.itcast.oa.core.dao.HibernateDao;

/**
 * HibernateDaoImpl
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午4:59:49
 * @version 1.0
 */
public class HibernateDaoImpl implements HibernateDao {
	
	/** 注入SessionFactory */
	private SessionFactory sessionFactory;
	
	/**
	 * 添加的方法
	 * @param entity
	 */
	public <T> void save(T entity){
		sessionFactory.getCurrentSession().save(entity);
	}
	/**
	 * 查询方法
	 * @param hql
	 * @return
	 */
	public <T> List<T> find(String hql){
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
