package org.happybirds.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.core.dao.HibernateDao;

/**
 * 部门数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:11:46
 * @version 1.0
 */
public interface DeptDao extends HibernateDao {
	/**
	 * TODO 查询部门id与部门名称
	 * @return
	 * @Since 2016年7月20日
	 * @Version 1.0
	 */
	List<Map<String, Object>> getDeptByIdAndName();


}
