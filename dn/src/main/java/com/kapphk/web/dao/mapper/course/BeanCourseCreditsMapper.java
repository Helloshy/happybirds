package com.kapphk.web.dao.mapper.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.course.BeanCourseCredits;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键ID的数据操作接口
 * @author zoneyu 2016-11-25
*/
public interface BeanCourseCreditsMapper extends BaseMapper<BeanCourseCredits, Long> {
	
	/**
	 * 查询当月抵扣卷使用情况
	 * @author dengwen 
	 * 2016-11-25下午4:02:42
	 */
	List<Map<String,Object>> findCourseCreditsList(@Param("startTime")String startTime, @Param("endTime")String endTime);


	String findUseCoupon(@Param("list")List<String> list, @Param("type")int type);
}