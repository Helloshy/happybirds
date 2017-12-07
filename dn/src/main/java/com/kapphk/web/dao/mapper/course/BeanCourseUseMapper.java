package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseUse;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键ID的数据操作接口
 * @author zoneyu 2016-11-25
*/
public interface BeanCourseUseMapper extends BaseMapper<BeanCourseUse, Long> {

	/**
	 * 查询当月的课程消耗数据
	 * @author dengwen 
	 * 2016-11-25下午2:08:07
	 */
	List<Map<String,Object>> findCourseUseList(@Param("startTime")String startTime, @Param("endTime")String endTime);
}