package com.kapphk.yyt.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.UserNumber;
import com.kapphk.yyt.bean.UserOrder;
import com.kapphk.yyt.mapper.UserNumberMapper;
import com.kapphk.yyt.mapper.UserOrderMapper;
import com.kapphk.yyt.service.OrderService;

/**
 * 订单服务
 * @author Administrator
 *
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<UserOrder, Long>
		implements OrderService {

	@Autowired
	private UserOrderMapper mapper ;
	@Autowired
	private UserNumberMapper numberMapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}


	@Override
	public Result saveOrder(UserOrder order, Result result) {
		UserNumber number = numberMapper.findById(order.getUnid());
		BigDecimal amount = number.getBalanceAmount();
		BigDecimal balanceAmount = amount.subtract(order.getAmount());
		number = new UserNumber();
		number.setId(order.getUnid());
		number.setBalanceAmount(balanceAmount);
		order.setBalanceAmount(balanceAmount);
		mapper.insert(order);
		numberMapper.update(number);
		return result;
	}


}
