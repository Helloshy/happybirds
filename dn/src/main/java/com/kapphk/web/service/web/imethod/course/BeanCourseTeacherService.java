package com.kapphk.web.service.web.imethod.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourseTeacher;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
/**
 * 主讲老师业务层
 * @author dengwen
 * 2016-09-26 18:12:53
 */
public interface BeanCourseTeacherService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanCourseTeacher bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCourseTeacher bean,MultipartFile file, HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCourseTeacher bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;
}
