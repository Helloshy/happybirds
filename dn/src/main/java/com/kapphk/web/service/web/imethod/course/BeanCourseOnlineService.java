package com.kapphk.web.service.web.imethod.course;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOnline;
import com.kapphk.web.utils.Result;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
/**
 * 网络课程业务层
 * @author dengwen
 * 2016-09-27 11:57:56
 */
public interface BeanCourseOnlineService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, String courseGroup, String teacherName,
			BigDecimal amount, String itemName,Long teacherId, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs,BeanCourseOnline online,BeanCourse course,MultipartFile file,MultipartFile file1,List<String> ct,
			HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCourseOnline bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	public void exportExcel(String courseGroup, String teacherName,
			BigDecimal amount, String itemName, Long teacherId,HttpServletResponse response);

}
