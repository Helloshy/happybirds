package com.kapphk.web.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 数据操作接口
 * @author zoneyu 2016-05-24
*/
public interface BeanFinancialMapper {
	
	/**
	 * 课程提成列表
	 * @author dengwen 
	 * 2016-11-7下午1:39:29
	 */
	public List<Map<String, Object>> findList(@Param("startTime")String startTime,@Param("endTime")String endTime,
			@Param("itemName")String itemName,@Param("param")String param,
			@Param("type")String type,@Param("page")int page, @Param("rows")int rows);

	/**
	 * 课程提成列表数
	 * @author dengwen 
	 * 2016-11-7下午1:40:29
	 */
	public int findCount(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("itemName")String itemName,
			@Param("param")String param,@Param("type")String type);
}