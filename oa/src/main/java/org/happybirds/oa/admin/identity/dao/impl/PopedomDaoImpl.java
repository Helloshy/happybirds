package org.happybirds.oa.admin.identity.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.AdminConstant;
import org.happybirds.oa.admin.identity.dao.PopedomDao;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;
import org.happybirds.oa.core.exception.OAException;
/**
 * 权限数据访问接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:12:33
 * @version 1.0
 */
public class PopedomDaoImpl extends HibernateDaoImpl implements PopedomDao {
	
	/**
	 * TODO   查询对应的操作
	 * @param moduleCode
	 * @param id
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getOperasByModuleIdAndRoleId(
			String moduleCode, Long id) {
		try {
			String hql="select opera.code from Popedom where module.code=? and role.id=?";
			List<Object> params = new ArrayList<>();
			params.add(moduleCode);
			params.add(id);
			return (List<String>) this.find(hql, params.toArray());
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询操作发生异常");
		}
	}
	/**
	 * TODO   删除权限
	 * @param id
	 * @param moduleCode
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	public void deletePopedomByRoleIdAndModuleCode(Long id, String moduleCode){
		try {
			String hql="delete from Popedom where module.code=? and role.id=?";
			this.bulkUpdate(hql, new Object[]{moduleCode,id});
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除权限出现异常");
		}
		
	}
	/**
	 * TODO    根据用户id查询所拥有的角色绑定的权限操作模块
	 * @return
	 * @Since 2016年7月31日
	 * @Version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<String> getModuleAndOperaByUserId(){
		try {
			String userId = AdminConstant.getUserInSession().getUserId();
			StringBuilder hql = new StringBuilder();
			hql.append("select module.code from Popedom where role.id in");
			hql.append("(select r.id from Role r inner join r.users as u where u.userId=?) ");
			hql.append("group by module.code order by module.code asc");
			
			return (List<String>) this.find(hql.toString(), new Object[]{userId});
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询用户菜单树出现异常");
		}
	}
	/**
	 * TODO   根据用户id查询对应的操作权限
	 * @param userId 用户id
	 * @return [{popedomCode:"",popedomName:""},{popedomCode:"",popedomName:""}...]
	 * @Since 2016年8月1日
	 * @Version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<String> getOperasCodeByUserId(String userId){
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select opera.code  from Popedom where role.id in");
			hql.append("(select r.id from Role r inner join r.users as u where u.userId=?) ");
			hql.append("group by opera.code order by opera.code asc");//用户与角色多对多关系，同一资源可能被多个角色持有，必须分组去重复
			
			return (List<String>) this.find(hql.toString(), new Object[]{userId});
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询用户菜单树出现异常");
		}
	}
}
