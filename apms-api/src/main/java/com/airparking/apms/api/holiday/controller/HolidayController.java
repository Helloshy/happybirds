package com.airparking.apms.api.holiday.controller;

import com.airparking.apms.api.carModel.entity.CarModel;
import com.airparking.apms.api.holiday.entity.Holiday;
import com.airparking.apms.api.holiday.service.HolidayService;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/5/23.
 */
@Controller("holiday")
public class HolidayController extends AbstractController{
    @Autowired
    private HolidayService holidayService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Authorize(login = false)
    @Path("return")
    public ServiceResponse holidayBack(ServiceRequest request) {
        Map<String, Object> map = null;
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Holiday> holidays = holidayService.findByIsSend(Boolean.FALSE);
        if (holidays != null){
            for (Holiday holiday:holidays) {
                try {
                    map = new HashMap<String, Object>();
                    map.put("name",holiday.getName());
                    map.put("startTime", dateFormat.format(holiday.getStartTime()));
                    map.put("endTime", dateFormat.format(holiday.getEndTime()));
                    map.put("days", holiday.getDays());
                    mapList.add(map);
                    holiday.setSend(true);
                    holidayService.update(holiday);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServiceException(e);
                }finally {
                    continue;
                }
            }
        }
        return new ServiceResponse(mapList);
    }

}
