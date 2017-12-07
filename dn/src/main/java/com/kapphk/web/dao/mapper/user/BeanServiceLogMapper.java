package com.kapphk.web.dao.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.user.BeanServiceLog;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 的数据操作接口
 * @author zoneyu 2016-11-04
*/
public interface BeanServiceLogMapper extends BaseMapper<BeanServiceLog, Long> {

	/**
	 * 回访记录列表
	 * @author dengwen 
	 * 2016-11-4上午10:31:38
	 */
	List<Map<String, Object>> findList(@Param("param")BeanServiceLog bean, @Param("userName")String userName,
			@Param("realName")String realName, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 回访记录列表总数
	 * @author dengwen 
	 * 2016-11-4上午10:31:57
	 */
	int findCount(@Param("param")BeanServiceLog bean, @Param("userName")String userName,
			@Param("realName")String realName);
}