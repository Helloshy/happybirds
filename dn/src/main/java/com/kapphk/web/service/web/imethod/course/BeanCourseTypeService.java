package com.kapphk.web.service.web.imethod.course;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;
/**
 * 课程系列业务层
 * @author dengwen
 * 2016-09-23 15:39:31
 */
public interface BeanCourseTypeService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanCourseType bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCourseType bean,List<String> ctt) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCourseType bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 课程系列列表
	 */
	public List<Map<String, Object>> searchCourseTypeList(Integer type);

}
