package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-10-12
*/
public interface BeanCourseCollectionMapper extends BaseMapper<BeanCourseCollection, Long> {

	/**
	 * APP-获取课程收藏列表
	 * @author dengwen 
	 * 2016-10-14上午11:27:54
	 */
	public List<Map<String, Object>> getCollectionList(@Param("uid")Long uid,@Param("page")int page,@Param("rows")int rows);

	
}