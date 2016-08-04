package org.happybirds.oa.admin.identity.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.identity.dao.UserDao;
import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;
import org.springframework.util.StringUtils;


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
	/**
	 * TODO   多条件分页查询用户
	 * @param user 查询条件对象
	 * @param pageModel 分页实体对象
	 * @return List<User>
	 * @Since 2016年7月20日
	 * @Version 1.0
	 */
	@Override
	public List<User> getUserByPage(User user, PageModel pageModel) {
		/**定义集合封装查询参数*/
		List<Object> params = new ArrayList<>(); 
		/**定义查询hql*/
		StringBuilder queryString = new StringBuilder();
		queryString.append("select u from org.happybirds.oa.admin.identity.entity.User as u where 1=1  ");
		/**根据条件拼接参数*/
		if(user!=null){
			if(!StringUtils.isEmpty(user.getName())){
				queryString.append("  and u.name like ?");
				params.add("%"+user.getName()+"%");
			}
			if(!StringUtils.isEmpty(user.getPhone())){
				queryString.append("  and u.phone like ?");
				params.add("%"+user.getPhone()+"%");
			}
			if(!StringUtils.isEmpty(user.getDept())
					&&user.getDept().getId()!=null
					&&user.getDept().getId()>0){
				queryString.append("  and u.dept.id = ?");
				params.add(user.getDept().getId());
			}
		}
		
		 List<User> users = findByPage(queryString.toString(), pageModel, params);
		 return users;
	}
	/**
	 * 
	 * TODO  批量删除用户
	 * @param ids
	 * @Since 2016年7月24日
	 * @Version 1.0
	 */
	@Override
	public void deleteUser(String[] ids) {
		StringBuilder hql = new StringBuilder();
		hql.append("delete from User where userId in (");
		for (int i = 0; i < ids.length; i++) {
			hql.append(i==0?"?":",?");			
		}
		hql.append(" )");
		this.bulkUpdate(hql.toString(), ids);
	}
	
	
}
