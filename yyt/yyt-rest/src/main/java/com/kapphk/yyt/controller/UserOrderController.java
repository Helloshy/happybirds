package com.kapphk.yyt.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.controller.BaseController;
import com.kapphk.system.bean.Guide;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.bean.UserNumber;
import com.kapphk.yyt.bean.UserOrder;
import com.kapphk.yyt.service.OrderService;

/**
 * 订单控制器
 * @author Administrator
 */
@RestController
@RequestMapping("/web/order")
public class UserOrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 支付成功后把单子记录写进user_order表
	 * @param uid
	 * @param unid
	 * @param companyId
	 * @param payMethod
	 * @param amount
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Result save(@RequestParam(required=true)Long uid
			,@RequestParam(required=true)Long unid
			,@RequestParam(required=true)Long companyId
			,@RequestParam(required=true)Integer payMethod
			,@RequestParam(required=true)BigDecimal amount){
		Result result = new Result("返回成功");
		try {
			UserOrder order = new UserOrder();
			order.setAmount(amount);
			order.setUid(uid);
			order.setUnid(unid);
			order.setCompanyId(companyId);
			order.setCreateTime(new Date());
			order.setPayMethod(payMethod);
			orderService.saveOrder(order,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(MSG.ERROR.getMsg());
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
	
	
	
	
	
}
