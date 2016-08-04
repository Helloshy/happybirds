package org.happybirds.oa.admin.identity.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.happybirds.oa.admin.identity.dao.RoleDao;
import org.happybirds.oa.admin.identity.entity.Role;
import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;
import org.happybirds.oa.core.exception.OAException;
/**
 * 角色数据访问接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:12:33
 * @version 1.0
 */
public class RoleDaoImpl extends HibernateDaoImpl implements RoleDao {

	@Override
	public List<Role> getRoleByPage(PageModel pageModel) {
		StringBuilder hql = new StringBuilder();
		try {
			hql.append(" from Role  order by id asc");
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询角色出现异常");
		}
		return this.findByPage(hql.toString(), pageModel, null);
	}
	
	/**
	 * TODO  根据角色id查询对象
 	 * @param id
	 * @return
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	@Override
	public Role getRoleById(Long id) {
		try {
			return this.get(Role.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * TODO  批量删除角色
	 * @param ids
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	@Override
	public void delete(Long[] ids) {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("delete from Role where  id in (");
			for (int i = 0; i < ids.length; i++) {
				hql.append(i==0?"?":",?");			
			}
			hql.append(" )");
			this.bulkUpdate(hql.toString(), ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<User> getBindUserByRoleId(Long id,PageModel pageModel){
		try {
			String hql = "select u from User u inner join u.roles as r where r.id = ? order by u.createDate asc";
			List<Object> params = new ArrayList<>();
			params.add(id);
			return this.findByPage(hql, pageModel, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询角色绑定用户出现异常");
		}
		
	}
	
	/**
	 * TODO   分页查询角色未绑定的对象
	 * @param id
	 * @param pageModel
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	public List<User> getUnBindUserByRoleId(Long id, PageModel pageModel){
		try {
//			String hql = "select u from User u inner join u.roles as r where r.id <>? and r.id<>? order by u.createDate asc";为什么不行呢？
			StringBuilder hql = new StringBuilder();
			hql.append("select u from User u where u.userId not in");
			hql.append("(select us.userId from User us inner join us.roles as r where r.id=? )");
			hql.append("order by u.createDate asc ");
			List<Object> params = new ArrayList<>();
			params.add(id);
			return this.findByPage(hql.toString(), pageModel, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询角色绑定用户出现异常");
		}
	}

}
