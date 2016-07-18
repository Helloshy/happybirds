package org.happybirds.oa.admin.identity.dao.impl;

import org.happybirds.oa.admin.identity.dao.UserDao;
import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;


/**
 * 用户数据访问接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:12:33
 * @version 1.0
 */
public class UserDaoImpl extends HibernateDaoImpl implements UserDao {
	
	/**
	 * 根据用户id(MD5加密)查询用户
	 * @param userId 用户id(MD5加密)
	 * @return 用户
	 */
	public User getUser(String userId){
		String hql = "select u from User u where MD5(u.userId) = ?";
		return this.findUniqueEntity(hql, userId);
	}
}
