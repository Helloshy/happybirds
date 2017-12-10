package com.airparking.apms.api.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.order.entity.Order;
import com.airparking.apms.api.order.entity.ParkCoupon;
import com.airparking.apms.api.order.service.OrderService;
import com.airparking.apms.api.order.service.ParkCouponService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.DateHelper;
import com.airparking.core.comm.utils.JsonUtils;
import com.airparking.core.comm.utils.PropertiesUtils;

/**
 * Created by ryan on 2016-07-26.
 */
@Controller("order")
@Scope("prototype")
public class OrderController extends AbstractController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ParkCouponService parkCouponService;

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	public ServiceResponse start(ServiceRequest request) {
		String data = request.getString("data");
		Long parkId = request.getLong("parkId");
		ResponseCode result = ResponseCode.SUCCESS;

		try {
			Map<String, Object> dataMap = JsonUtils.toObject(data, Map.class);
			Order order = JsonUtils.toObject(data, Order.class);
			order.setPayAmount(
					dataMap.get("payAmount") != null ? (int) (((Double) dataMap.get("totalAmount")) * 100) : 0);
			order.setTotalAmount(
					dataMap.get("totalAmount") != null ? (int) (((Double) dataMap.get("totalAmount")) * 100) : 0);
			order.setDeleted(false);
			order.setParkId(parkId);
			order.setOrderStatus(Order.OrderStatus.START.getValue());
			if (!orderService.existed((order.getTradeNo()))) {
				if (orderService.save(order) <= 0) {
					result = ResponseCode.UPDATE_ORDER_ERROR;
				}
			}
		} catch (IOException e) {
			throw new ServiceException(e);
		}

		// System.out.println("start 的线程名称是:"+Thread.currentThread().getName() +
		// "的耗时时间"+ (endTime - startTime) +
		// "hascode值是"+Thread.currentThread().hashCode());
		return new ServiceResponse(result);
	}

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	public ServiceResponse paid(ServiceRequest request) {
		String data = request.getString("data");
		ParkCoupon parkCoupon = null;
		ResponseCode result = ResponseCode.SUCCESS;

		try {
			Map<String, Object> dataMap = JsonUtils.toObject(data, Map.class);
			Order order = JsonUtils.toObject(data, Order.class);
			order.setOrderStatus(Order.OrderStatus.PAID.getValue());
			int totalAmount = dataMap.get("totalAmount") != null ? (int) (((Double) dataMap.get("totalAmount")) * 100)
					: 0;
			int payAmount = dataMap.get("payAmount") != null ? (int) (((Double) dataMap.get("payAmount")) * 100) : 0;
			order.setTotalAmount(totalAmount);
			order.setPayAmount(payAmount);

			if (orderService.existed(order.getTradeNo())) {
				Order paidOrder = orderService.findByTradeNoAndType(order.getTradeNo());
				Integer status = paidOrder.getOrderStatus();
				if (status == Order.OrderStatus.START.getValue() || status == Order.OrderStatus.PAID.getValue()
						|| status == Order.OrderStatus.SECONDPAID.getValue()) {// 只允许未支付或多次支付的订单进入
					int oldOrderStatus = Order.OrderStatus.START.getValue();
					int orderStatus = Order.OrderStatus.PAID.getValue();
					if (paidOrder != null) {
						if (status.equals(Order.OrderStatus.PAID.getValue())) {// 二次支付
							// payAmount = payAmount + paidOrder.getPayAmount();
							orderStatus = Order.OrderStatus.SECONDPAID.getValue();
							oldOrderStatus = Order.OrderStatus.PAID.getValue();
						}
					}
					if (orderService.updateByTradeNoAndOrderStatus(order.getTradeNo(), oldOrderStatus, orderStatus,
							totalAmount, payAmount) <= 0) {
						result = ResponseCode.UPDATE_ORDER_ERROR;
					}
				}
			} else {
				order.setDeleted(false);
				order.setParkId(request.getLong("parkId"));
				if (orderService.save(order) <= 0) {
					result = ResponseCode.UPDATE_ORDER_ERROR;
				}
			}

			Order order1 = orderService.findByTradeNoAndType(order.getTradeNo());
			if (order1 != null && order1.getId() != null && order != null) {
				parkCoupon = parkCouponService.findByCode(order.getCode());
				if (parkCoupon != null) {
					parkCoupon.setOrderId(order1.getId());
					parkCoupon.setStatus((byte) 1);
					parkCouponService.update(parkCoupon);
				}
			}
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		// System.out.println("paid 的线程名称是:"+Thread.currentThread().getName() +
		// "paid 的耗时时间"+ (endTime - startTime) +
		// "hascode值是"+Thread.currentThread().hashCode());
		return new ServiceResponse(result);
	}

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	public ServiceResponse end(ServiceRequest request) {// 自动支付
		String data = request.getString("data");
		ParkCoupon parkCoupon = null;
		ResponseCode result = ResponseCode.SUCCESS;

		try {
			Map<String, Object> dataMap = JsonUtils.toObject(data, Map.class);
			Order order = JsonUtils.toObject(data, Order.class);
			order.setOrderStatus(Order.OrderStatus.AUTOEND.getValue());
			int totalAmount = dataMap.get("totalAmount") != null ? (int) (((Double) dataMap.get("totalAmount")) * 100)
					: 0;
			int payAmount = dataMap.get("payAmount") != null ? (int) (((Double) dataMap.get("payAmount")) * 100) : 0;
			order.setTotalAmount(totalAmount);
			order.setPayAmount(payAmount);

			if (orderService.existed((order.getTradeNo()))) {
				if (orderService.updateByTradeNoToEnd(order.getTradeNo(), Order.OrderStatus.AUTOEND.getValue(),
						totalAmount, payAmount, order.getEndTime(), order.getUpdatedDate(),
						order.getTotalTime()) <= 0) {
					result = ResponseCode.UPDATE_ORDER_ERROR;
				}
			} else {
				order.setDeleted(false);
				order.setParkId(request.getLong("parkId"));
				if (orderService.save(order) <= 0) {
					result = ResponseCode.UPDATE_ORDER_ERROR;
				}
			}
			List<Order> orders = orderService.findByTradeNo(order.getTradeNo());
			if (orders.size() > 0) {
				for (Order order1 : orders) {
					if (order1 != null && order1.getId() != null && order != null) {
						parkCoupon = parkCouponService.findByCode(order.getCode());
						if (parkCoupon != null) {
							parkCoupon.setOrderId(order1.getId());
							parkCoupon.setStatus((byte) 1);
							parkCouponService.update(parkCoupon);
						}
					}
				}
			}
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		// System.out.println("end 的线程名称是:"+Thread.currentThread().getName() +
		// "end 的耗时时间"+ (endTime - startTime) +
		// "hascode值是"+Thread.currentThread().hashCode());
		return new ServiceResponse(result);
	}

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	public ServiceResponse endByMan(ServiceRequest request) {// 手动支付
		String data = request.getString("data");
		ParkCoupon parkCoupon = null;
		ResponseCode result = ResponseCode.SUCCESS;

		try {
			Map<String, Object> dataMap = JsonUtils.toObject(data, Map.class);
			Order order = JsonUtils.toObject(data, Order.class);
			order.setOrderStatus(Order.OrderStatus.ENDBYMAN.getValue());
			int totalAmount = dataMap.get("totalAmount") != null ? (int) (((Double) dataMap.get("totalAmount")) * 100)
					: 0;
			int payAmount = dataMap.get("payAmount") != null ? (int) (((Double) dataMap.get("payAmount")) * 100) : 0;
			order.setTotalAmount(totalAmount);
			order.setPayAmount(payAmount);

			if (orderService.existed(order.getTradeNo())) {
				if (orderService.updateByTradeNoToEnd(order.getTradeNo(), Order.OrderStatus.ENDBYMAN.getValue(),
						totalAmount, payAmount, order.getEndTime(), order.getUpdatedDate(),
						order.getTotalTime()) <= 0) {
					result = ResponseCode.UPDATE_ORDER_ERROR;
				}
			} else {
				order.setDeleted(false);
				order.setParkId(request.getLong("parkId"));
				if (orderService.save(order) <= 0) {
					result = ResponseCode.UPDATE_ORDER_ERROR;
				}
			}
			List<Order> orders = orderService.findByTradeNo(order.getTradeNo());
			if (orders.size() > 0) {
				for (Order order1 : orders) {
					if (order1 != null && order1.getId() != null && order != null) {
						parkCoupon = parkCouponService.findByCode(order.getCode());
						if (parkCoupon != null) {
							parkCoupon.setOrderId(order1.getId());
							parkCoupon.setStatus((byte) 1);
							parkCouponService.update(parkCoupon);
						}
					}
				}
			}
		} catch (IOException e) {
			throw new ServiceException(e);
		}

		return new ServiceResponse(result);
	}

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	public ServiceResponse discard(ServiceRequest request) {
		String data = request.getString("data");
		// Long parkId = request.getLong("parkId");

		try {
			Order order = JsonUtils.toObject(data, Order.class);
			order.setOrderStatus(Order.OrderStatus.DISCARD.getValue());

			if (orderService.existed(order.getTradeNo())) {
				orderService.updateByTradeNo(order);
			} else {
				order.setDeleted(false);
				order.setParkId(request.getLong("parkId"));
				orderService.save(order);
			}
		} catch (IOException e) {
			throw new ServiceException(e);
		}

		return new ServiceResponse(ResponseCode.SUCCESS);
	}

	@Authorize(login = false)
	public ServiceResponse onlinePaid(ServiceRequest request) {
		return new ServiceResponse(orderService.findLatestOnlinePaid(request.getLong("parkId"),
				Integer.parseInt(PropertiesUtils.get("online.paid.req.intervals"))));
	}

	// @RequiredParams(params = { "data" })
	@Authorize(login = false)
	@Path("back")
	public ServiceResponse back(ServiceRequest request) {
		Order order = null;
		Map<String, Object> orderMap = null;
		List<Map<String, Object>> dataList = null;
		String data = request.getString("data");
		try {
			List<Map<String, Object>> orderIdList = JsonUtils.toObject(data, List.class);
			dataList = new ArrayList<>();
			for (Map<String, Object> map : orderIdList) {
				orderMap = new HashMap<>();
				String orderId = (String) map.get("orderId");
				order = orderService.findByTradeNoAndType(orderId);
				if (order != null) {
					String code = "";
					orderMap.put("orderId", orderId);
					ParkCoupon parkCoupon = parkCouponService.findByOrderId(order.getId());
					if (parkCoupon != null) {
						code = parkCoupon.getCode();
					}
					orderMap.put("coupobCode", code);
					orderMap.put("totalAmount", order.getTotalAmount() == null ? 0 : order.getTotalAmount());
					orderMap.put("payAmount", order.getPayAmount() == null ? 0 : order.getPayAmount());
					orderMap.put("payDate",
							order.getUpdatedDate() == null ? "" : DateHelper.formatDatetime(order.getUpdatedDate()));
					orderMap.put("rateId", order.getRateId());
				}
				dataList.add(orderMap);
			}

			String start = DateHelper.formatDatetime(DateUtils.truncate(new Date(), Calendar.DATE));
			String end = DateHelper.formatDatetime(DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DATE), 1));
			List<Order> orderToday = null;
			// for(int i=0;i<2;i++){//最多执行两次
			orderToday = orderService.findOrderToday(start, end, request.getLong("parkId"));
			if (orderToday != null) {
				for (Order o2 : orderToday) {
					String code = "";
					orderMap = new HashMap<>();
					orderMap.put("orderId", o2.getTradeNo() == null ? "0" : o2.getTradeNo());
					ParkCoupon parkCoupon = parkCouponService.findByOrderId(o2.getId());
					if (parkCoupon != null) {
						code = parkCoupon.getCode();
					}
					orderMap.put("coupobCode", code);
					orderMap.put("totalAmount", o2.getTotalAmount() == null ? 0 : o2.getTotalAmount());
					orderMap.put("payAmount", o2.getPayAmount() == null ? 0 : o2.getPayAmount());
					orderMap.put("payDate",
							o2.getUpdatedDate() == null ? "" : DateHelper.formatDatetime(o2.getUpdatedDate()));
					orderMap.put("rateId", o2.getRateId());
					dataList.add(orderMap);
				}
			}
			// }

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

		return new ServiceResponse(dataList);
	}
}