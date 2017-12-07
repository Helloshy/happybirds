package com.kapphk.web.service.web.imethod.course;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseSystem;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
/**
 * 系统课程管理业务层
 * @author dengwen
 * 2016-10-12 16:43:01
 */
public interface BeanCourseSystemService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanCourse bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCourse bean, List<Long> courseOffline, List<Long> courseOnline,
			HttpServletRequest request, MultipartFile file, String itemRemark) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCourseSystem bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	public void exportExcel(BeanCourse bean, HttpServletResponse response);

}
