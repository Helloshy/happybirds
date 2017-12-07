package com.kapphk.web.dao.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.user.BeanReportTrans;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-11-16
*/
public interface BeanReportTransMapper extends BaseMapper<BeanReportTrans, Long> {

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-11-16上午11:09:46
	 */
	List<Map<String, Object>> findList(@Param("realName")String realName, @Param("startTime")String startTime,
			@Param("endTime")String endTime, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询列表数
	 * @author dengwen 
	 * 2016-11-16上午11:09:53
	 */
	int findCount(@Param("realName")String realName, @Param("startTime")String startTime,@Param("endTime")String endTime);
}