package com.kapphk.web.controller.web.course;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseSystem;
import com.kapphk.web.service.web.imethod.course.BeanCourseSystemService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 系统课程管理控制层
 * @author dengwen
 * 2016-10-12 16:43:01
 */
@RestController
@RequestMapping("/web/course/coursesystem/")
public class BeanCourseSystemController {

	@Autowired
	private BeanCourseSystemService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanCourse bean, GridReq gridReq){
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
	public Result saveData(BeanCourse bean,Long[] courseOffline,Long[] courseOnline,
			HttpServletRequest request,MultipartFile file,String itemRemark){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,ValidateUtils.isempty(courseOffline) ? null : Arrays.asList(courseOffline),
					ValidateUtils.isempty(courseOnline) ? null : Arrays.asList(courseOnline),request,file,itemRemark);
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
	public Result getData(BeanCourseSystem bean){
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
	public void exportExcel(BeanCourse bean,HttpServletResponse response){
		service.exportExcel(bean,response);
	}

}
