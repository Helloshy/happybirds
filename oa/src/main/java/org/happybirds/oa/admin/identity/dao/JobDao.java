package org.happybirds.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.core.dao.HibernateDao;
/**
 * 职位数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:20:06
 * @version 1.0
 */
public interface JobDao extends HibernateDao {
	/**
	 * TODO   查询所有的职位信息
	 * @return List<Map<String, Object>>
	 * @Since 2016年7月21日
	 * @Version 1.0
	 */
	List<Map<String, Object>> getJobByCodeAndName();

}
