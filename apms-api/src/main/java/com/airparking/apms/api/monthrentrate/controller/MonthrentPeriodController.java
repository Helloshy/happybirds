package com.airparking.apms.api.monthrentrate.controller;

/**
 * 分时段月保
 *
 * @author
 * @create 2017-05-19-17:26
 */

import com.airparking.apms.api.monthrentrate.service.MonthrentPeriodService;
import com.airparking.apms.api.monthrentrate.service.MonthrentrateService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller("monthrentPeriod")
public class MonthrentPeriodController extends AbstractController{

	@Autowired
	private MonthrentPeriodService monthrentPeriodService;

	@Autowired
	private MonthrentrateService monthrentrateService;

	private static final Logger logger = LoggerFactory.getLogger(MonthrentPeriodController.class);

	@RequiredParams(params = { "data" })
	@Authorize(login = false)
	@Path("save")
	public ServiceResponse add(ServiceRequest request) {
		String data = request.getString("data");
		Long parkId = request.getLong("parkId");
		String appId = request.getString("appId");

		try {
			List<Map<String, Object>> mrrList = JsonUtils.toObject(data, List.class);
			for (Map<String, Object> mrr : mrrList) {
				ServiceResponse response = monthrentPeriodService.createPeriod(mrr,parkId,appId);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return new ServiceResponse(ResponseCode.SUCCESS);
	}

	@Authorize(login = false)
	@Path("return")
	public ServiceResponse returnBack(ServiceRequest request) {
		JSONArray ja = new JSONArray();
		//Long parkId = request.getLong("parkId");

		try {
			/*Date now = new Date();
			Date startYear = DateUtils.truncate(now, Calendar.DATE);
			Date endYear = DateUtils.truncate(DateUtils.addDays(now, 1), Calendar.DATE);
			List<Monthrentrate> mrList = monthrentrateService.findByThisYearWithParkId(parkId, startYear,
					endYear);*/
			ja = monthrentPeriodService.returnPeriod(ja);
		} catch (Exception e) {
			logger.debug(e.toString());
			 e.printStackTrace();
			return new ServiceResponse(ResponseCode.SERVER_ERROR);
		}

		return new ServiceResponse(ja);
	}
}
