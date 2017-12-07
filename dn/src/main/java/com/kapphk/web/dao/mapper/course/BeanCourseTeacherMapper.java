package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseTeacher;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 的数据操作接口
 * @author zoneyu 2016-09-26
*/
public interface BeanCourseTeacherMapper extends BaseMapper<BeanCourseTeacher, Long> {

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-9-27上午10:15:54
	 */
	public int findCount(@Param("param")BeanCourseTeacher bean);

	/**
	 * 查询主讲老师列表
	 * @author dengwen 
	 * 2016-9-27上午10:19:52
	 */
	public List<Map<String, Object>> findList(@Param("param")BeanCourseTeacher bean, @Param("page")int page,
			@Param("rows")int rows);

	/**
	 * 获取动能讲师课程列表
	 * @author dengwen 
	 * 2016-10-17下午4:38:38
	 */
	public List<Map<String, Object>> findCourseList(@Param("id")Long id,@Param("page")int page,
			@Param("rows")int rows);

}