package com.kapphk.web.controller.web.course;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseStudent;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.service.web.imethod.course.BeanCourseOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 报名联系人控制层
 * @author dengwen
 * 2016-09-27 10:02:20
 */
@RestController
@RequestMapping("/web/course/order/")
public class BeanCourseOrderController {

	@Autowired
	private BeanCourseOrderService service;

	@SuppressWarnings("unused")
	@InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
	
	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(Long uid,Long[] couponId,Integer payStatus, Integer isUse, String orderNo, String itemName,
						String realName, String startTime, String endTime, String type, GridReq gridReq, Integer isPay){
		Result rs = new Result();
		try {
			return service.searchList(rs,uid,couponId,payStatus,isUse,orderNo,itemName,realName,startTime,endTime,type,gridReq,isPay);
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
	public Result saveData(BeanCourseOrder bean,String userName){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,userName);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 课程详情
	 */
	@RequestMapping("getCourseDetail.htm")
	public Result getCourseDetail(Long id){
		Result rs = new Result();
		try {
			return service.getCourseDetail(rs,id);
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
	@RequestMapping("upstatus.htm")
	public Result upstatus(BeanCourseOrder bean){
		Result rs = new Result();
		try {
			return service.upstatus(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 获取课程列表
	 * @author dengwen 
	 * 2016-10-25上午11:02:23
	 */
	@RequestMapping("getCourseList.htm")
	public List<Map<String,Object>> getCourseList(String type){
		return service.getCourseList(type);
	}
	
	/**
	 * 用户详情
	 * @author dengwen 
	 * 2016-10-25下午1:38:29
	 */
	@RequestMapping("getUserDetail.htm")
	public Result getUserDetail(BeanUser bean){
		Result rs = new Result();
		try {
			return service.getUserDetail(rs,bean);
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
	 * 2016-10-25下午5:30:14
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(Long uid,Long[] couponId,Integer payStatus, Integer isUse, String orderNo, String itemName,
			String realName, String startTime, String endTime, String type,HttpServletResponse response,Integer isPay){
		service.exportExcel(uid,couponId,payStatus,isUse,orderNo,itemName,realName,startTime,endTime,type,response,isPay);
	}
	
	/**
	 * 保存孩子信息
	 * @author dengwen 
	 * 2016-12-6上午11:25:13
	 */
	@RequestMapping("saveCourseStudent.htm")
	public Result saveCourseStudent(BeanCourseStudent bean){
		Result rs = new Result();
		try {
			return service.saveCourseStudent(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 订单详情
	 * @author dengwen 
	 * 2016-12-6下午2:22:23
	 */
	@RequestMapping("courseOrderDetail.htm")
	public Result courseOrderDetail(Long id){
		Result rs = new Result();
		try {
			return service.courseOrderDetail(rs,id);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

}
