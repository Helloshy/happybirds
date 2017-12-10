package com.airparking.apms.api.parkSituation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.device.entity.Device;
import com.airparking.apms.api.device.service.DeviceService;
import com.airparking.apms.api.system.entity.System;
import com.airparking.apms.api.system.service.SystemService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;

/**
 * Created by Administrator on 2017/5/12.
 */
@Controller("parkSituation")
public class ParkSitaunController extends AbstractController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private SystemService systemService;

    @RequiredParams(params = {"data"})
    @Path("getInfo")
    @Authorize(login = false)
    public ServiceResponse post(ServiceRequest request){
        Long parkId = request.getLong("parkId");
        try {
            String data = request.getString("data");
            Map<String, Object> info = JsonUtils.toObject(data, Map.class);
            Map<String, Object> sys = (Map<String, Object>) info.get("sysInfo");

            String sysIp = (String) sys.get("sysIp");
            Byte sysStatus = Byte.parseByte((String) sys.get("sysStatus"));
            //修改系统状态
            System system = systemService.findByIp(sysIp);
            if (system != null) {
                system.setStatus(sysStatus);
                system.setUpdatedDate(new Date());
                systemService.update(system);
            }

            List<Map<String, Object>> deviceInfoList = (List<Map<String,Object>>) info.get("deviceInfo");
            for (Map<String, Object> deviceInfo : deviceInfoList ) {
                String deviceIp = (String) deviceInfo.get("deviceIp");
                Byte deviceStatus = Byte.parseByte((String)deviceInfo.get("deviceStatus"));
                //修改设备状态
                Device device = deviceService.findByIp(deviceIp);
                if (device != null) {
                    device.setStatus(deviceStatus);
                    device.setUpdatedDate(new Date());
                    deviceService.update(device);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ServiceResponse(ResponseCode.SUCCESS);
    }
}
