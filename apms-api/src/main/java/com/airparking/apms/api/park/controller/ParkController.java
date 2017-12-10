package com.airparking.apms.api.park.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.park.entity.Park;
import com.airparking.apms.api.park.service.ParkService;
import com.airparking.apms.api.parkPrice.service.ParkPriceService;
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
 * Created by ryan on 2016-08-01.
 */
@Controller("park")
public class ParkController extends AbstractController {
  @Autowired
  private ParkService parkService;
  @Autowired
  private ParkPriceService parkPriceService;
  
  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  @Path("save")
  public ServiceResponse savePrice(ServiceRequest request) {
    String data = request.getString("data");

    try {
      List<Map<String, Object>> parkList = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> pa : parkList) {
        Park park = null;
        Long parkId = request.getLong("parkId");
        if (parkId != null) {
          park = parkService.findById(parkId);
        }

        if (park == null) {
          return new ServiceResponse(ResponseCode.MISS_REQUIRED);
        }

        park.setFixSpace(Integer.valueOf((String) pa.get("fixSpace")));
        park.setFlags(Byte.valueOf((String) pa.get("flags")));
        park.setFreeTime(Integer.valueOf((String) pa.get("freeTime")));
        park.setMonthDiedDays(Byte.valueOf((String) pa.get("monthDiedDays")));
        park.setNoTempEndDate(DateHelper.parseDateTime((String) pa.get("noTempEndDate")));
        park.setNoTempStartDate(DateHelper.parseDateTime((String) pa.get("noTempStartDate")));
        park.setTempSpace(Integer.valueOf((String) pa.get("tempSpace")));
        park.setTotalSpace(Integer.valueOf((String) pa.get("totalSpace")));
        park.setUpdatedDate(new Date());
        parkService.update(park);
        
        //同步滞留时间
        parkPriceService.updateByParkId(parkId, park.getFreeTime());
        
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }

  @Authorize(login = false)
  @Path("sendPark")
  public ServiceResponse sendPark(ServiceRequest request) {
    List<Map<String, Object>> list = null;
    try {
		list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		String now = DateFormatUtils.format(new Date(),"yyyy-MM-dd");
		String startDate = now +" 00:00:00";
		String endDate = now +" 23:59:59";
		List<Park> parkList = parkService.findAllByDate(startDate,endDate);
      	for (Park park : parkList) {
			int staus = 0; // 0:有效 1:无效
			map = new HashMap<String, Object>();
			map.put("parkId", park.getId());
			map.put("name", park.getPname());
			map.put("address", park.getAddress());
			map.put("contact", park.getContact());
			map.put("latitude", park.getLatitude());
			map.put("longitude", park.getLongitude());
			map.put("mobile", park.getMobile());
			map.put("dayParting",park.getIsDayParting());
        if (!park.getIsDeleted()) {
          if (park.getIsDisabled()) {
            staus = 1;
          }
        } else {
          staus = 1;
        }
        map.put("status", staus);
        list.add(map);
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(list);
  }
}