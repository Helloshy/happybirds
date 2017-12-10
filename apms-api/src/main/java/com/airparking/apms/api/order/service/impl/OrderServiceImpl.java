package com.airparking.apms.api.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airparking.apms.api.order.entity.Order;
import com.airparking.apms.api.order.mapper.OrderMapper;
import com.airparking.apms.api.order.service.OrderService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-07-26.
 */
@Service("orderService")
public class OrderServiceImpl extends AbstractService implements OrderService {
  @Autowired
  private OrderMapper orderMapper;

  @Override
  public int save(Order order) {
    return orderMapper.add(order);
  }

  @Override
  public void updateByTradeNo(Order order) {
    orderMapper.updateByTradeNo(order);
  }

  @Override
  public boolean existed(String tradeNo) {
    return orderMapper.countByTradeNo(tradeNo) > 0;
  }

  @Override
  public List<Order> findLatestOnlinePaid(Long parkId, int intervals) {
    return orderMapper.findLatestOnlinePaid(parkId, intervals);
  }

  @Override
  public List<Order> findByTradeNo(String tradeNo) {
    return orderMapper.findByTradeNo(tradeNo);
  }

  @Override
  public List<Order> findOrderToday(String start, String end, Long parkId) {
    return orderMapper.findOrderToday(start, end, parkId);
  }

  @Override
  public List<Order> findByIsSend() {
    return orderMapper.findByIsSend();
  }

  @Override
  public int update(Order order) {
    return orderMapper.update(order);
  }

  @Override
  public Order getByTradeNo(String tradeNo) {
    return orderMapper.getByTradeNo(tradeNo);
  }

  @Override
  public Order getByCardNo(String cardNo) {
    return orderMapper.getByCardNo(cardNo);
  }

  @Override
  public int updateOrderIsSend(String tradeNo) {
    return orderMapper.updateOrderIsSend(tradeNo);
  }

	@Override
	@Transactional
	public void updateByTradeNoStatusToPaid(String tradeNo, int value) {
		orderMapper.updateByTradeNoStatusToPaid(tradeNo,value);
	}

  @Override
  @Transactional
  public int updateByTradeNoAndOrderStatus(String tradeNo, Integer oldOrderStatus, Integer orderStatus,
      Integer totalAmount, Integer payAmount) {
    return orderMapper.updateByTradeNoAndOrderStatus(tradeNo, oldOrderStatus, orderStatus, totalAmount, payAmount);
  }

  @Override
  @Transactional
  public int updateByTradeNoToEnd(String tradeNo, Integer orderStatus, Integer totalAmount
      						,Integer payAmount,Date endTime,Date updatedDate, Long totalTime) {
    return orderMapper.updateByTradeNoToEnd(tradeNo, orderStatus, totalAmount, payAmount,endTime,updatedDate,totalTime);
  }

	@Override
	public Order findByTradeNoAndType(String tradeNo) {
		return orderMapper.findByTradeNoAndType(tradeNo);
	}

//	@Override
//	@Transactional
//	public int updateByMonthrentToEnd(String tradeNo, int value, Date endTime,Date updatedDate, Integer totalAmount
//			,Integer payAmount,Byte type) {
//		return orderMapper.updateByMonthrentToEnd(tradeNo, value, endTime,updatedDate,totalAmount,payAmount,type);
//	}
}