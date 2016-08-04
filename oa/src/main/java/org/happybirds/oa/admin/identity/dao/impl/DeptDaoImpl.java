package org.happybirds.oa.admin.identity.dao.impl;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.identity.dao.DeptDao;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;
/**
 * 部门数据访问接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:12:33
 * @version 1.0
 */
public class DeptDaoImpl extends HibernateDaoImpl implements DeptDao {
	/**
	 * 
	 * TODO  查询所有的部门id与部门名称
	 * @return
	 * @Since 2016年7月20日
	 * @Version 1.0
	 */
	@Override
	public List<Map<String, Object>> getDeptByIdAndName() {
		String hql = "select new map(id as id,name as name) from Dept order by id asc";
		return this.find(hql);
	}

}
