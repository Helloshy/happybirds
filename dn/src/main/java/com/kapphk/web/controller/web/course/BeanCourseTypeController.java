package com.kapphk.web.controller.web.course;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.service.web.imethod.course.BeanCourseTypeService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 课程系列控制层
 * @author dengwen
 * 2016-09-23 15:39:31
 */
@RestController
@RequestMapping("/web/course/coursetype/")
public class BeanCourseTypeController {

	@Autowired
	private BeanCourseTypeService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanCourseType bean, GridReq gridReq){
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
	public Result saveData(BeanCourseType bean,String[] ctt){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,Arrays.asList(ctt));
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
	public Result getData(BeanCourseType bean){
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
	 * 获取全部课程系列名称
	 * @author dengwen 
	 * 2016-9-26上午11:46:46
	 */
	@RequestMapping("searchCourseTypeList.htm")
	public List<Map<String,Object>> searchCourseTypeList(Integer type){
		return service.searchCourseTypeList(type);
	}
}
