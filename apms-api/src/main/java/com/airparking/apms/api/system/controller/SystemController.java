package com.airparking.apms.api.system.controller;

import com.airparking.apms.api.system.entity.System;
import com.airparking.apms.api.system.service.SystemService;
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

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */
@Controller("system")
public class SystemController extends AbstractController {

    @Autowired
    private SystemService systemService;

    @RequiredParams(params = {"data"})
    @Path("save")
    @Authorize(login = false)
    public ServiceResponse post(ServiceRequest request){
        try {
            Long parkId = request.getLong("parkId");
            String data = request.getString("data");
            List<Map<String, Object>> systemList = JsonUtils.toObject(data, List.class);

            for(Map<String, Object> sys : systemList){
                String ip = (String)sys.get("ip");
                System system = systemService.findByIp(ip);
                if (system == null){
                    system = new System();
                    system.setName((String)sys.get("name"));
                    system.setIp(ip);
                    system.setParkId(parkId);
                    system.setStatus((byte)System.SystemStatus.NORMAL.getValue());
                    system.setDeleted(false);
                    system.setCreatedDate(new Date());
                    system.setUpdatedDate(system.getCreatedDate());
                    systemService.save(system);
                }
            }
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return new ServiceResponse(ResponseCode.SUCCESS);
    }
}
