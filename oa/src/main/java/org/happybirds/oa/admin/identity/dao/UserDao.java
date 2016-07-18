package org.happybirds.oa.admin.identity.dao;

import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.core.dao.HibernateDao;


/**
 * 用户数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:11:46
 * @version 1.0
 */
public interface UserDao extends HibernateDao {
	/**
	 * 根据用户id(MD5加密)查询用户
	 * @param userId 用户id(MD5加密)
	 * @return 用户
	 */
	User getUser(String userId);
}
