package com.kapphk.web.controller.web.course;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourseTeacher;
import com.kapphk.web.service.web.imethod.course.BeanCourseTeacherService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 主讲老师控制层
 * @author dengwen
 * 2016-09-26 18:12:53
 */
@RestController
@RequestMapping("/web/course/courseteacher/")
public class BeanCourseTeacherController {

	@Autowired
	private BeanCourseTeacherService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanCourseTeacher bean, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,bean,gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping("save.htm")
	public Result saveData(BeanCourseTeacher bean,MultipartFile file,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,file,request);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 详情
	 */
	@RequestMapping("data.htm")
	public Result getData(BeanCourseTeacher bean){
		Result rs = new Result();
		try {
			return service.getData(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("delete.htm")
	public Result delete(Long[] ids){
		Result rs = new Result();
		try {
			return service.delete(rs,Arrays.asList(ids));
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

}
