package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseSystem;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-12
*/
public interface BeanCourseSystemMapper extends BaseMapper<BeanCourseSystem, Long> {

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-10-12下午5:43:35
	 */
	List<Map<String, Object>> findList(@Param("param")BeanCourse bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-10-12下午5:45:09
	 */
	int findCount(@Param("param")BeanCourse bean);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-10-12下午6:19:21
	 */
	Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 获取网络课程关联的系统课程
	 * @author dengwen 
	 * 2016-12-7上午10:19:51
	 */
	List<Long> findByCourseId(@Param("ids")List<Long> ids);
}