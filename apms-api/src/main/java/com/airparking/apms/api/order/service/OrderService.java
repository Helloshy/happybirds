package com.airparking.apms.api.order.service;

import java.util.Date;
import java.util.List;

import com.airparking.apms.api.order.entity.Order;

/**
 * Created by ryan on 2016-07-26.
 */
public interface OrderService {
	int save(Order order);

	void updateByTradeNo(Order order);

	boolean existed(String tradeNo);

	List<Order> findLatestOnlinePaid(Long parkId, int intervals);

	List<Order> findByTradeNo(String tradeNo);

	List<Order> findOrderToday(String start, String end, Long parkId);

	List<Order> findByIsSend();

	int update(Order order);

	Order getByTradeNo(String tradeNo);

	Order getByCardNo(String cardNo);
  
	int updateByTradeNoAndOrderStatus(String tradeNo, Integer oldOrderStatus, Integer orderStatus,

	Integer totalAmount, Integer payAmount);
  
	int updateOrderIsSend(String tradeNo);

	void updateByTradeNoStatusToPaid(String tradeNo, int value);
	
	int updateByTradeNoToEnd(String tradeNo, Integer orderStatus
								,Integer totalAmount, Integer payAmount,Date endTime,Date updatedDate, Long totalTime);

	Order findByTradeNoAndType(String tradeNo);

//	int updateByMonthrentToEnd(String tradeNo, int value, Date endTime,Date updatedDate,Integer totalAmount, Integer payAmount,Byte type);
}