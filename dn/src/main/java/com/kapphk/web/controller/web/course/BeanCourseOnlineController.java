package com.kapphk.web.controller.web.course;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOnline;
import com.kapphk.web.service.web.imethod.course.BeanCourseOnlineService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 网络课程控制层
 * @author dengwen
 * 2016-09-27 11:57:56
 */
@RestController
@RequestMapping("/web/course/courseconline/")
public class BeanCourseOnlineController {

	@Autowired
	private BeanCourseOnlineService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(String courseGroup, String teacherName,BigDecimal amount,String itemName,Long teacherId,GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,courseGroup,teacherName,amount,itemName,teacherId,gridReq);
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
	public Result saveData(BeanCourseOnline online,BeanCourse course,MultipartFile file,MultipartFile file1,String[] ct,
			HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveData(rs,online,course,file,file1,Arrays.asList(ct),request);
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
	public Result getData(BeanCourseOnline bean){
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

	/**
	 * 导出
	 * @author dengwen 
	 * 2016-12-26下午5:32:05
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(String courseGroup, String teacherName,BigDecimal amount,String itemName,Long teacherId,HttpServletResponse response){
		service.exportExcel(courseGroup,teacherName,amount,itemName,teacherId,response);
	}
	
}
