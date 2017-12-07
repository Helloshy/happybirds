package com.kapphk.web.service.web.imethod.course;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseStudent;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

public interface BeanCourseOrderService {

	/**
	 * 订单列表
	 * @author dengwen 
	 * 2016-10-24下午4:16:01
	 */
	public Result searchList(Result rs, Long uid,Long[] couponId, Integer payStatus, Integer isUse,
			String orderNo, String itemName, String realName, String startTime,
			String endTime, String type, GridReq gridReq, Integer isPay)throws Exception;

	/**
	 * 添加订单
	 * @author dengwen 
	 * 2016-10-24下午4:32:04
	 */
	public Result saveData(Result rs, BeanCourseOrder bean, String userName)throws Exception;

	/**
	 * 取消订单
	 * @author dengwen 
	 * 2016-10-24下午4:33:10
	 */
	public Result upstatus(Result rs, BeanCourseOrder bean)throws Exception;

	/**
	 * 获取课程列表
	 * @author dengwen 
	 * 2016-10-25上午11:04:12
	 */
	public List<Map<String, Object>> getCourseList(String type);

	/**
	 * 课程详情
	 * @author dengwen 
	 * 2016-10-25上午11:13:06
	 */
	public Result getCourseDetail(Result rs, Long id)throws Exception;

	/**
	 * 用户详情
	 * @author dengwen 
	 * 2016-10-25下午1:39:33
	 */
	public Result getUserDetail(Result rs, BeanUser bean)throws Exception;

	/**
	 * 导出
	 * @author dengwen 
	 * 2016-10-25下午5:30:45
	 */
	public void exportExcel(Long uid, Long[] couponId, Integer payStatus,
			Integer isUse, String orderNo, String itemName, String realName,
			String startTime, String endTime, String type,HttpServletResponse response,Integer isPay);

	/**
	 * 保存孩子信息
	 * @author dengwen 
	 * 2016-12-6上午11:26:12
	 */
	public Result saveCourseStudent(Result rs, BeanCourseStudent bean)throws Exception;

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-12-6下午2:22:58
	 */
	public Result courseOrderDetail(Result rs, Long id)throws Exception;

}
