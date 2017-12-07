package com.kapphk.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kapphk.web.service.web.imethod.financial.BeanFinancialService;

@Component
public class ManagerBonus {

	@Autowired
	private BeanFinancialService service;
	
	/**
	 * 生成区域提成数据
	 * @author dengwen 
	 * 2016-11-21下午2:45:53
	 */
	public void managerBonus(){
		service.managerBonus();
		service.courseUse();
		service.courseCredits();
	}
	
	public void teacherOrder(){
		//更新陪伴或讲学订单状态
		service.teacherOrder();
		//更新商品订单收货状态
		service.productOrder();
	}
	
	/**
	 * 更新课程订单或者商品订单
	 * @author dengwen 
	 * 2016-12-24下午2:00:08
	 */
	public void cancellationOfOrder(){
		service.cancellationOfOrder();
	}
	
	
}
