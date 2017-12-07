package com.kapphk.web.service.web.imethod.course;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;
/**
 * 线下课程业务层
 * @author dengwen
 * 2016-09-26 10:35:01
 */
public interface BeanCourseService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanCourse bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCourse bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCourse bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 获取课程列表
	 * @author dengwen 
	 * 2016-9-26下午1:45:56
	 */
	public List<Map<String, Object>> searchCourseList(Integer recordType,String type);

}
