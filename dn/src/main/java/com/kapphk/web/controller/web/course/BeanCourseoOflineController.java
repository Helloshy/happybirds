package com.kapphk.web.controller.web.course;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOffline;
import com.kapphk.web.service.web.imethod.course.BeanCourseOfflineService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;


/**
 * 线下课程控制层
 * @author dengwen 
 * 2016-9-26下午1:37:00
 */
@RestController
@RequestMapping("/web/course/offline/")
public class BeanCourseoOflineController {
	
	@Autowired
	private BeanCourseOfflineService service;
	
	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(String itemName,Long courseTypeId,Integer isRecommend,Integer state,BigDecimal amount,GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,itemName,courseTypeId,isRecommend,state,amount,gridReq);
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
	public Result saveData(BeanCourse course,BeanCourseOffline offline,MultipartFile file,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveData(rs,course,offline,file,request);
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
	public Result getData(BeanCourse bean){
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
	 * 省市区
	 * @author dengwen 
	 * 2016-9-26下午1:57:25
	 */
	@RequestMapping("searchPcd.htm")
	public List<Map<String,Object>> searchPcd(String id,Integer type){
		return service.searchPcd(id,type);
	}
	
	/**
	 * 更新
	 * @author dengwen 
	 * 2016-9-26下午4:45:46
	 */
	@RequestMapping("saveCO.htm")
	public Result saveIsRecommend(BeanCourseOffline offline){
		Result rs = new Result();
		try {
			return service.saveIsRecommend(rs,offline);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 批量更新课程状态
	 * @author dengwen 
	 * 2016-9-26下午5:31:56
	 */
	@RequestMapping("upState.htm")
	public Result upState(Long[] ids,Integer state){
		Result rs = new Result();
		try {
			return service.upState(rs,Arrays.asList(ids),state);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 导出
	 * @author zoneyu 16-12-23
	 */
	@RequestMapping("export.htm")
	public void export(String itemName,Long courseTypeId,Integer isRecommend,Integer state,BigDecimal amount,
			HttpServletRequest request,HttpServletResponse response){
		try {
			service.export(itemName,courseTypeId,isRecommend,state,amount,request,response) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
