package com.kapphk.web.dao.mapper.course;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseOnline;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-09-23
*/
public interface BeanCourseOnlineMapper extends BaseMapper<BeanCourseOnline, Long> {

	/**
	 * 查询网络课程列表
	 * @author dengwen 
	 * 2016-9-27下午1:56:46
	 */
	public List<Map<String, Object>> findList(@Param("courseGroup")String courseGroup, @Param("teacherName")String teacherName,
			@Param("amount")BigDecimal amount, @Param("itemName")String itemName, @Param("teacherId")Long teacherId,
			@Param("page")int page, @Param("rows")int rows);

	public int findCount(@Param("courseGroup")String courseGroup, @Param("teacherName")String teacherName,
			@Param("amount")BigDecimal amount, @Param("itemName")String itemName, @Param("teacherId")Long teacherId);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-27下午2:17:55
	 */
	public Map<String, Object> findDetailById(@Param("id")Long id);
}