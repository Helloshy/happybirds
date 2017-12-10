package com.airparking.apms.api.lane.controller;

import com.airparking.apms.api.lane.entity.Lane;
import com.airparking.apms.api.lane.service.LaneService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */
@Controller("lane")
public class LaneController extends AbstractController {

    @Autowired
    private LaneService laneService;

    @RequiredParams(params = {"data"})
    @Path("save")
    @Authorize(login = false)
    public ServiceResponse post(ServiceRequest request){
        Long parkId = request.getLong("parkId");
        String data = request.getString("data");
        try {
            List<Map<String, Object>> laneList = JsonUtils.toObject(data, List.class);
            for (Map<String, Object> le : laneList) {
                try {
                    Lane lane = new Lane();
                    lane.setName((String) le.get("name"));
                    lane.setIp((String)le.get("ip"));
                    lane.setParkId(parkId);
                    lane.setDeleted(false);
                    lane.setCreatedDate(new Date());
                    lane.setUpdatedDate(lane.getCreatedDate());
                    laneService.save(lane);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }

        return new ServiceResponse(ResponseCode.SUCCESS);
    }
}
