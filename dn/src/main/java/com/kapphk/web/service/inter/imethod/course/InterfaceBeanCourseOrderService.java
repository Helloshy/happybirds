package com.kapphk.web.service.inter.imethod.course;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseStudent;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanCourseOrderService extends BaseService<BeanCourseOrder, Long> {

	/**
	 * 报名课程
	 * @author zoneyu 16-10-14
	 */
	public Result saveOrder(BeanCourseOrder bean, BeanCourseStudent student, String typeName, 
			Integer isCash, BigDecimal cash, String root, Integer isPublic, HttpServletRequest request,HttpServletResponse response,Result rs) throws Exception ;

	/**
	 * 是否享受复训价
	 * @author zoneyu 16-10-14
	 */
	public Result checkInfo(BeanCourseOrder bean, Result rs) throws Exception ;

	/**
	 * 支付成功获取订单信息
	 * @author zoneyu 16-10-17
	 */
	public Result getOrderInfo(BeanCourseOrder bean, Result rs) throws Exception ;

	/**
	 * 课程信息
	 * @author zoneyu 16-10-18
	 */
	public Result getCourseInfo(BeanCourseOrder bean, Result rs) throws Exception;

	/**
	 * 学员信息
	 * @author zoneyu 16-10-18
	 */
	public Result getStudentInfo(Long id, Integer type, Result rs) throws Exception;

	/**
	 * 验证课程信息
	 * @author zoneyu 16-10-18
	 */
	public Result passed(Long id, Long uid, Result rs) throws Exception;

	/**
	 * 赞赏记录
	 * @author zoneyu 16-10-20
	 */
	public Result getAdmirationList(Long courseId ,Integer page , Result rs) throws Exception;

	/**
	 * 课程订单--线下课程
	 * @author zoneyu 16-10-24
	 */
	public Result getOfflineOrderList(Long uid, Integer status, Integer page, Result rs) throws Exception;

	/**
	 * 课程订单--网络课程
	 * @author zoneyu 16-10-24
	 */
	public Result getOnlineOrderList(Long uid, Integer page, Result rs) throws Exception;

	/**
	 * 获取个人管理的客户数据
	 * @author dengwen 
	 * 2016-11-11下午2:03:40
	 */
	public Result getClientList(Long uid, String param, Integer page, Result rs) throws Exception;

	/**
	 * 获取客户课程订单列表
	 * @author dengwen 
	 * 2016-11-11下午2:09:11
	 */
	public Result getCourseOrderList(Long uid, Long id, Integer page, Result rs) throws Exception;

	/**
	 * 获取课程订单详情
	 * @author dengwen 
	 * 2016-11-12上午10:16:05
	 */
	public Result getCourseOrderDetail(Long id, Long uid, Result rs) throws Exception;

	/**
	  * 保存系统课程
	  * @author zoneyu 16-11-22
	  */
	public Result saveSystemOrder(BeanCourseOrder bean,String root,HttpServletRequest request,HttpServletResponse response, Result rs) throws Exception;

	/**
	 * 取消订单返蓝币红币
	 * @author dengwen 
	 * 2016-12-12上午10:06:27
	 */
	public Result cancelOrder(String orderNo, Result rs) throws Exception;

}
