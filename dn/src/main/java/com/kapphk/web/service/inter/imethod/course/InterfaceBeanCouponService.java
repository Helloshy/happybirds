package com.kapphk.web.service.inter.imethod.course;

import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanCouponService {

	/**
	 * 获取抵扣卷列表
	 * @author dengwen 
	 * 2016-10-14上午10:37:39
	 */
	public Result getCouponList(BeanUserCoupon bean, Integer page, Result rs) throws Exception;

}
