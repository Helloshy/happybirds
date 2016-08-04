package org.happybirds.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.core.common.web.PageModel;
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
	/**
	 * TODO   多条件分页查询用户
	 * @param user 查询条件对象
	 * @param pageModel 分页实体对象
	 * @return List<User>
	 * @Since 2016年7月20日
	 * @Version 1.0
	 */
	List<User> getUserByPage(User user, PageModel pageModel);
	/**
	 * TODO   批量删除用户
	 * @param ids
	 * @Since 2016年7月24日
	 * @Version 1.0
	 */
	void deleteUser(String[] ids);
	
}
