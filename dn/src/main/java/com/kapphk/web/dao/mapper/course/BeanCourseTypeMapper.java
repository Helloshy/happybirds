package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-13
*/
public interface BeanCourseTypeMapper extends BaseMapper<BeanCourseType, Long> {
	
	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-9-23下午4:05:01
	 */
	public List<Map<String, Object>> findList(@Param("param")BeanCourseType bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询总数
	 * @author dengwen 
	 * 2016-9-23下午4:05:15
	 */
	public int findCount(@Param("param")BeanCourseType bean);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-23下午4:18:24
	 */
	public Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 获取全部课程系列名称
	 * @author dengwen 
	 * 2016-9-26上午11:46:38
	 */
	public List<Map<String, Object>> searchCourseTypeList();
}