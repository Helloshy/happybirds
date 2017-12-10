package com.airparking.apms.api.payment.mapper;

import org.apache.ibatis.annotations.Param;

import com.airparking.apms.api.payment.entity.Payment;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ryan on 2016-07-27.
 */
public interface PaymentMapper extends AbstractMapper<Payment, Long> {

	Integer findLastPaidFee(@Param("outTradeNo") String outTradeNo, @Param("plateNo") 
				String plateNo, @Param("parkId") Long parkId);

	@ResultType(Integer.class)
	@Select("select count(id) from payment where out_trade_no = #{outTradeNo} and created_date = #{createdDate}")
	int findRepetition(@Param("outTradeNo") String outTradeNo, @Param("createdDate") String createdDate);
}