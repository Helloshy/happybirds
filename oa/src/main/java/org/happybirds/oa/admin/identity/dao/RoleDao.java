package org.happybirds.oa.admin.identity.dao;

import java.util.List;

import org.happybirds.oa.admin.identity.entity.Role;
import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.HibernateDao;


/**
 * 角公数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:11:46
 * @version 1.0
 */
public interface RoleDao extends HibernateDao {
	/**
	 * TODO   分页查询角色
	 * @param pageModel
	 * @return
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	List<Role> getRoleByPage(PageModel pageModel);
	/**
	 * TODO  根据角色id查询对象
 	 * @param id
	 * @return
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	Role getRoleById(Long id);
	
	/**
	 * TODO   批量删除角色
	 * @param ids
	 * @Since 2016年7月27日
	 * @Version 1.0
	 */
	void delete(Long [] ids);
	/**
	 * TODO   分页查询角色绑定的对象
	 * @param id
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	List<User> getBindUserByRoleId(Long id,PageModel pageModel);
	/**
	 * TODO   分页查询角色未绑定的对象
	 * @param id
	 * @param pageModel
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	List<User> getUnBindUserByRoleId(Long id, PageModel pageModel);

}
