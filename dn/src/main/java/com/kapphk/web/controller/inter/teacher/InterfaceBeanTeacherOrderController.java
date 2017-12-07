package com.kapphk.web.controller.inter.teacher;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.teacher.BeanTeacherOrder;
import com.kapphk.web.service.inter.imethod.teacher.InterfaceBeanTeacherOrderService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 老师订单控制层
 * @author dengwen 
 * 2016-10-14下午2:10:01
 */
@RestController
@RequestMapping("/teacherorder/")
public class InterfaceBeanTeacherOrderController {

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	@Autowired
	private InterfaceBeanTeacherOrderService service;
	
	/**
	 * 保存预约
	 * @author dengwen 
	 * 2016-10-20下午2:30:00
	 */
	@RequestMapping("saveTeacherOrder.do")
	public Result saveTeacherOrder(BeanTeacherOrder bean){
		Result rs = new Result() ;
		try {
			rs = service.saveTeacherOrder(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 获取订单列表
	 * @author dengwen 
	 * 2016-10-20下午2:30:27
	 */
	@RequestMapping("getTeacherOrderList.do")
	public Result getTeacherOrderList(BeanTeacherOrder bean,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.getTeacherOrderList(bean,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 支付订单
	 * @author dengwen 
	 * 2016-10-20下午3:46:41
	 */
	@RequestMapping("savePayOrder.do")
	public Result savePayOrder(BeanTeacherOrder bean){
		Result rs = new Result() ;
		try {
			rs = service.savePayOrder(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 获取订单详情
	 * @author dengwen 
	 * 2016-10-20下午4:49:11
	 */
	@RequestMapping("getOrderDetail.do")
	public Result getOrderDetail(BeanTeacherOrder bean){
		Result rs = new Result() ;
		try {
			rs = service.getOrderDetail(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 取消订单
	 * @author dengwen 
	 * 2016-10-20下午4:49:11
	 */
	@RequestMapping("cancelOrder.do")
	public Result cancelOrder(BeanTeacherOrder bean){
		Result rs = new Result() ;
		try {
			rs = service.cancelOrder(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
		
	/**
	 * 获取列表最大抵扣例
	 * @author dengwen 
	 * 2016-11-12上午11:14:36
	 */
	@RequestMapping("getSettingDetail.do")
	public Result getSettingDetail(BeanSetting bean){
		Result rs = new Result() ;
		try {
			rs = service.getSettingDetail(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 预约详情
	 * @author zoneyu 16-11-22
	 */
	@RequestMapping("searchTeacherOrderDetail.do")
	public Result searchTeacherOrderDetail(Long id){
		Result rs = new Result() ;
		try {
			rs = service.searchTeacherOrderDetail(id,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
