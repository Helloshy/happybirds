package com.airparking.apms.api.payment.service;

import com.airparking.apms.api.payment.entity.Payment;

/**
 * Created by ryan on 2016-07-27.
 */
public interface PaymentService {
  void save(Payment payment);
  Integer findLastPaidFee(String outTradeNo, String plateNo, Long parkId);

	int findRepetition(String outTradeNo, String createdDate);
}