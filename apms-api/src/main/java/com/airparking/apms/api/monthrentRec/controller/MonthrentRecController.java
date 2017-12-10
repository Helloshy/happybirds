package com.airparking.apms.api.monthrentRec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.monthrent.entity.Monthrent;
import com.airparking.apms.api.monthrent.service.MonthrentService;
import com.airparking.apms.api.monthrentRec.entity.MonthrentRec;
import com.airparking.apms.api.monthrentRec.service.MonthrentRecService;
import com.airparking.apms.api.monthrentrate.entity.Monthrentrate;
import com.airparking.apms.api.monthrentrate.service.MonthrentrateService;
import com.airparking.apms.comm.DateFormats;
import com.airparking.apms.comm.RocketMQ;
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
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by ryan on 2016-07-28.
 */
@Controller("monthrentRec")
public class MonthrentRecController extends AbstractController {
	@Autowired
	private MonthrentRecService monthrentRecService;

	@Autowired
	private MonthrentService monthrentService;

	@Autowired
	private MonthrentrateService monthrentrateService;

	private static final Logger logger = LoggerFactory.getLogger(MonthrentRecController.class);

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	@Path("save")
	public ServiceResponse add(ServiceRequest request) {
		String data = request.getString("data");
		Long parkId = request.getLong("parkId");

		try {
			List<Map<String, Object>> mrList = JsonUtils.toObject(data, List.class);
			for (Map<String, Object> mr : mrList) {
				String mrId = (String) mr.get("mrid");// 月保流水号
				String mrrId = (String) mr.get("mrrid");// 月保费率流水号
				String operationTime = (String) mr.get("operationTime");// 线下操作时间

				if (StringUtils.isBlank(mrId) || StringUtils.isBlank(mrrId)) {
					return new ServiceResponse(ResponseCode.MISS_REQUIRED);
				}

				Monthrent monthrent = monthrentService.findByMrid(mrId);
				Monthrentrate monthrentrate = monthrentrateService.findByMrrId(mrrId);
				if (monthrent == null || monthrentrate == null) {
					logger.info("SAVE MONTHRENTREC FAIL, mrId:{}, mrrId:{}", mrId, mrrId);
					return new ServiceResponse(ResponseCode.MISS_REQUIRED);
				}

				Boolean isAdd = true;
				MonthrentRec monthrentRec = new MonthrentRec();
				if (monthrentRecService.findBySerialMridAndCreatedDate(monthrent.getSerialMrid(),
						operationTime) != null) {
					isAdd = false;
					monthrentRec = monthrentRecService.findBySerialMridAndCreatedDate(monthrent.getSerialMrid(),
							operationTime);
				}

				monthrentRec.setAmount(
						(int) (mr.get("amount") != null ? Double.valueOf((String) mr.get("amount")) * 100 : 0));
				monthrentRec.setCarmode(mr.get("carmode") != null ? (String) mr.get("carmode") : null);
				monthrentRec.setDeleted(false);
				Byte status = Byte.valueOf((String) mr.get("status"));
				monthrentRec.setDisabled(status);
				monthrentRec.setEndDate(DateHelper.parseDateTime((String) mr.get("endDate")));
				monthrentRec.setIsDeleted((byte) 0);
				monthrentRec.setMainPlateNo((String) mr.get("mainPlateNo"));
				monthrentRec.setOperationType(Byte.valueOf((String) mr.get("operationType")));
				monthrentRec.setOwnerMobile(mr.get("ownerMobile") != null ? (String) mr.get("ownerMobile") : null);
				monthrentRec.setOwnerName(mr.get("ownerName") != null ? (String) mr.get("ownerName") : null);
				monthrentRec.setParkId(parkId);
				monthrentRec.setPaymentType(Byte.valueOf((String) mr.get("paymentType")));
				monthrentRec.setRemark(mr.get("remark") != null ? (String) mr.get("remark") : null);
				monthrentRec.setSerialMrid(monthrent.getSerialMrid() != null ? monthrent.getSerialMrid() : mrId);
				monthrentRec.setSerialMrrid(
						monthrentrate.getSerialMrrid() != null ? monthrentrate.getSerialMrrid() : mrrId);
				monthrentRec.setSpaceNo(mr.get("spaceNo") != null ? (String) mr.get("spaceNo") : null);
				monthrentRec.setStartDate(DateHelper.parseDateTime((String) mr.get("startDate")));
				monthrentRec.setStatus(status);

				Date createdDate = DateFormats.parseDateTimeDispalyFormat(operationTime);
				if (isAdd) {
					monthrentRec.setCreatedDate(createdDate);
					monthrentRecService.save(monthrentRec);
				} else {
					monthrentRec.setUpdatedDate(createdDate);
					monthrentRecService.update(monthrentRec);
				}

				sendMonthOrderMqToPoly(request, mr, monthrentRec);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return new ServiceResponse(ResponseCode.SUCCESS);
	}

	private void sendMonthOrderMqToPoly(ServiceRequest request, Map<String, Object> mr, MonthrentRec monthrentRec)
			throws JsonProcessingException {
		Map<String, Object> mqData = new HashMap<>();
		mqData.put("tradeNo", monthrentRec.getId() + "");
		mqData.put("appId", request.getAppId());
		mqData.put("plateNo", (String) mr.get("mainPlateNo"));
		mqData.put("startTime", (String) mr.get("startDate"));
		mqData.put("endTime", (String) mr.get("endDate"));
		mqData.put("paidAmount",
				(int) (mr.get("amount") != null ? Double.valueOf((String) mr.get("amount")) * 100 : 0));
		mqData.put("spaceCode", mr.get("spaceNo") != null ? (String) mr.get("spaceNo") : null);
		mqData.put("payTime", DateHelper.formatDatetime(new Date()));
		mqData.put("payMethod", getPaymentType(Byte.valueOf((String) mr.get("paymentType"))));
		SendResult send = new RocketMQ().producer().send(new Message("AP_TRADE_TOPIC", "PolyMonthOrderToOrdercollector",
				"POLY_MONTHRENT_ORDER_ID_" + monthrentRec.getId(), JsonUtils.toJson(mqData).getBytes()));
		logger.info("POLY_MONTHRENT_ORDER_MESSAGEID:"+send.getMessageId());
	}

	private Integer getPaymentType(Byte paymentWay) {

		Integer paymentType = 0;
		switch (paymentWay) {
		case 0:
			paymentType = 0;
			break;

		default:
			paymentType = 6;
			break;
		}
		return paymentType;
	}
}