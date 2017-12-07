package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseContact;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 的数据操作接口
 * @author zoneyu 2016-09-26
*/
public interface BeanCourseContactMapper extends BaseMapper<BeanCourseContact, Long> {

	/**
	 * 查询报名联系人列表
	 * @author dengwen 
	 * 2016-9-27上午10:33:58
	 */
	public List<Map<String, Object>> findList(@Param("param")BeanCourseContact bean, @Param("page")int page,
			@Param("rows")int rows);

	/**
	 * c查询联系人总数
	 * @author dengwen 
	 * 2016-9-27上午10:34:30
	 */
	public int findCount(@Param("param")BeanCourseContact bean);

	/**
	 * 查询教务人员列表
	 * @author dengwen 
	 * 2016-9-27上午10:49:21
	 */
	public List<Map<String, Object>> findContactList();
}