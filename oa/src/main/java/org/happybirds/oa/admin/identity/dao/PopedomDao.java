package org.happybirds.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.core.dao.HibernateDao;
/**
 * 权限数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:11:46
 * @version 1.0
 */
public interface PopedomDao extends HibernateDao {
	/**
	 * TODO   查询对应的操作
	 * @param moduleCode
	 * @param id
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	List<String> getOperasByModuleIdAndRoleId(String moduleCode, Long id);
	/**
	 * TODO   删除权限
	 * @param id
	 * @param moduleCode
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	void deletePopedomByRoleIdAndModuleCode(Long id, String moduleCode);
	/**
	 * TODO    根据用户id查询所拥有的角色绑定的权限操作模块
	 * @return
	 * @Since 2016年7月31日
	 * @Version 1.0
	 */
	 List<String> getModuleAndOperaByUserId();
	 /**
	 * TODO   根据用户id查询对应的操作权限
	 * @param userId 用户id
	 * @return [{popedomCode:"",popedomName:""},{popedomCode:"",popedomName:""}...]
	 * @Since 2016年8月1日
	 * @Version 1.0
	 */
	 List<String> getOperasCodeByUserId(String userId);

}
