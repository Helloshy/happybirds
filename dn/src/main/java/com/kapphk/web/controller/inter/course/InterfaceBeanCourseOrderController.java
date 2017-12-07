package com.kapphk.web.controller.inter.course;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseStudent;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanCourseOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 课程订单
 * @author zoneyu 16-10-14
 */
@RestController
@RequestMapping("/courseOrder/")
public class InterfaceBeanCourseOrderController extends BaseController<BeanCourseOrder, Long> {

	@Autowired
	private InterfaceBeanCourseOrderService service;

	/**
	 * 报名课程
	 * @author zoneyu 16-10-14
	 */
	@RequestMapping("saveOrder.do")
	public Result saveOrder(BeanCourseOrder bean,String typeName,BeanCourseStudent student,
			Integer isCash,BigDecimal cash,String root,Integer isPublic,HttpServletRequest request,HttpServletResponse response){
		Result rs = new Result();
		try {
			return service.saveOrder(bean,student,typeName,isCash,cash,root,isPublic,request,response,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 是否享受复训价
	 * @author zoneyu 16-10-14
	 */
	@RequestMapping("checkInfo.do")
	public Result checkInfo(BeanCourseOrder bean){
		Result rs = new Result();
		try {
			return service.checkInfo(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 支付成功获取订单信息
	 * @author zoneyu 16-10-17
	 */
	@RequestMapping("getOrderInfo.do")
	public Result getOrderInfo(BeanCourseOrder bean){
		Result rs = new Result();
		try {
			return service.getOrderInfo(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 课程信息
	 * @author zoneyu 16-10-18
	 */
	@RequestMapping("getCourseInfo.do")
	public Result getCourseInfo(BeanCourseOrder bean){
		Result rs = new Result() ;
		try {
			rs = service.getCourseInfo(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 学员信息
	 * @author zoneyu 16-10-18
	 */
	@RequestMapping("getStudentInfo.do")
	public Result getStudentInfo(Long id,Integer type){
		Result rs = new Result() ;
		try {
			rs = service.getStudentInfo(id,type,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 验证课程信息
	 * @author zoneyu 16-10-18
	 */
	@RequestMapping("passed.do")
	public Result passed(Long id,Long uid){
		Result rs = new Result() ;
		try {
			rs = service.passed(id,uid,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 赞赏记录
	 * @author zoneyu 16-10-20
	 */
	@RequestMapping("getAdmirationList.do")
	public Result getAdmirationList(Long courseId,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.getAdmirationList(courseId,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 课程订单--线下课程
	 * @author zoneyu 16-10-24
	 */
	@RequestMapping("getOfflineOrderList.do")
	public Result getOfflineOrderList(Long uid,Integer status,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.getOfflineOrderList(uid,status,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 课程订单--网络课程
	 * @author zoneyu 16-10-24
	 */
	@RequestMapping("getOnlineOrderList.do")
	public Result getOnlineOrderList(Long uid,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.getOnlineOrderList(uid,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	  * 获取个人管理的客户数据
	  * @author dengwen 
	  * 2016-11-11上午10:12:02
	  */
	 @RequestMapping("getClientList.do")
	 public Result getClientList(Long uid,String param,Integer page){
		 Result rs = new Result() ;
		 try {
			 rs = service.getClientList(uid,param,page,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 获取客户课程订单列表
	  * @author dengwen 
	  * 2016-11-11上午10:12:02
	  */
	 @RequestMapping("getCourseOrderList.do")
	 public Result getCourseOrderList(Long uid,Long id,Integer page){
		 Result rs = new Result() ;
		 try {
			 rs = service.getCourseOrderList(uid,id,page,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 获取客户课程订单详情
	  * @author dengwen 
	  * 2016-11-11上午10:12:02
	  */
	 @RequestMapping("getCourseOrderDetail.do")
	 public Result getCourseOrderDetail(Long id,Long uid){
		 Result rs = new Result() ;
		 try {
			 rs = service.getCourseOrderDetail(id,uid,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 保存系统课程
	  * @author zoneyu 16-11-22
	  */
	 @RequestMapping("saveSystemOrder.do")
	 public Result saveSystemOrder(BeanCourseOrder bean,String root,HttpServletRequest request,HttpServletResponse response){
		 Result rs = new Result() ;
		 try {
			 rs = service.saveSystemOrder(bean,root,request,response,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 
	 /**
	  * 取消订单返蓝币红币
	  * @author dengwen
	  */
	 @RequestMapping("cancelOrder.do")
	 public Result cancelOrder(String orderNo){
		 Result rs = new Result() ;
		 try {
			 rs = service.cancelOrder(orderNo,rs) ;
		 } catch (Exception e) {
			 e.printStackTrace();
			 rs.setStatus(MSG.ERROR.getStatus()) ;
			 rs.setMsg("出现错误") ;
		 }
		 return rs ;
	 }
	 

}
