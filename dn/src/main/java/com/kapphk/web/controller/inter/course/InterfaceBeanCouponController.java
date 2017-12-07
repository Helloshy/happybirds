package com.kapphk.web.controller.inter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanCouponService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 课程抵扣卷控制层
 * @author dengwen 
 * 2016-10-14上午10:28:00
 */
@RestController
@RequestMapping("/coupon/")
public class InterfaceBeanCouponController {
	
	@Autowired
	private InterfaceBeanCouponService service;
	
	/**
	 * 获取抵扣卷列表
	 * @author dengwen 
	 * 2016-10-14上午10:35:41
	 */
	@RequestMapping("getCouponList.do")
	public Result getCouponList(BeanUserCoupon bean,Integer page){
		Result rs = new Result();
		try {
			rs = service.getCouponList(bean,page,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
