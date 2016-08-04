package org.happybirds.oa.admin.identity.dao.impl;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.identity.dao.JobDao;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;
/**
 * 职位数据访问接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:12:33
 * @version 1.0
 */
public class JobDaoImpl extends HibernateDaoImpl implements JobDao {
	
	/**
	 * TODO   查询所有的职位信息
	 * @return List<Map<String, Object>>
	 * @Since 2016年7月21日
	 * @Version 1.0
	 */
	@Override
	public List<Map<String, Object>> getJobByCodeAndName() {
		String hql="select new map (code as code,name as name) from Job order by code asc";
		return this.find(hql);
	}

}
