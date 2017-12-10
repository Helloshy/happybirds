package com.airparking.apms.api.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.payment.entity.Payment;
import com.airparking.apms.api.payment.mapper.PaymentMapper;
import com.airparking.apms.api.payment.service.PaymentService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-07-27.
 */
@Service("paymentService")
public class PaymentServiceImpl extends AbstractService implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public void save(Payment payment) {
      paymentMapper.add(payment);
    }

	@Override
	public Integer findLastPaidFee(String outTradeNo, String plateNo, Long parkId) {
		// TODO Auto-generated method stub
		return paymentMapper.findLastPaidFee(outTradeNo, plateNo, parkId);
	}

	@Override
	public int findRepetition(String outTradeNo, String createdDate) {
		return paymentMapper.findRepetition(outTradeNo,createdDate);
	}

}