package com.airparking.apms.api.payment.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.payment.entity.Payment;
import com.airparking.apms.api.payment.service.PaymentService;
import com.airparking.apms.comm.HashUtils;
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

/**
 * Created by ryan on 2016-07-27.
 */
@Controller("payment")
@Scope("prototype")
public class PaymentController extends AbstractController {
	@Autowired
	private PaymentService paymentService;

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	@Path("save")
	public ServiceResponse add(ServiceRequest request) {
		String data = request.getString("data");
		Long parkId = request.getLong("parkId");

		try {
			List<Map<String, Object>> pList = JsonUtils.toObject(data, List.class);
			for (Map<String, Object> p : pList) {
				String outTradeNo = (String) p.get("outTradeNo");
				String createdDate = (String) p.get("createdDate");
				//判断是否重复数据
		  		int sum = paymentService.findRepetition(outTradeNo,createdDate);
		  		if(sum > 0){
		  			continue;
		  		}

				String amount = (String) p.get("amount");
				if (StringUtils.isNotBlank(outTradeNo) && StringUtils.isNotBlank(amount)) {
					String plateNo = (String) p.get("plateNo");
					int at = (int) (Double.valueOf(amount) * 100);
					if(Byte.valueOf((String) p.get("type")) != (byte)2){//月保没有二次支付
						Integer lastPaidFee = paymentService.findLastPaidFee(outTradeNo, plateNo, parkId);
						if(lastPaidFee != null && lastPaidFee <= at){//二次或多次支付订单
							at = at - lastPaidFee;
						}
					}
				  Payment payment = new Payment();
				  payment.setAmount(at);
				  payment.setCreatedBy((String) p.get("createdBy"));
				  payment.setCreatedDate(DateHelper.parseDateTime(createdDate));
				  payment.setOutTradeNo(outTradeNo);
				  payment.setPaymentWay(p.get("paymentWay") != null ? Integer.valueOf((String) p.get("paymentWay")) : Payment.PaymentWay.OTHER.getValue());
				  payment.setTradeNo(HashUtils.md5(outTradeNo));
				  payment.setPlateNo(plateNo);
				  payment.setDeleted(false);
				  payment.setType(Byte.valueOf((String) p.get("type")));
				  payment.setParkId(parkId);
				  paymentService.save(payment);
				}
			  }
			} catch (Exception e) {
			  throw new ServiceException(e);
    		}

    	return new ServiceResponse(ResponseCode.SUCCESS);
  	}
}