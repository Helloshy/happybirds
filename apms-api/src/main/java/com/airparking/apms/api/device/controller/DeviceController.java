package com.airparking.apms.api.device.controller;

import com.airparking.apms.api.device.entity.Device;
import com.airparking.apms.api.device.service.DeviceService;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by spj19931234 on 2017/5/12.
 */
@Controller("device")
public class DeviceController extends AbstractController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private LaneService laneService;

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @RequiredParams(params = {"data"})
    @Path("save")
    @Authorize(login = false)
    public ServiceResponse post(ServiceRequest request) {
        Long parkId = request.getLong("parkId");
        String data = request.getString("data");
        try {
            List<Map<String, Object>> deviceList = JsonUtils.toObject(data, List.class);
            for (Map<String, Object> dv : deviceList) {
                String deviceName = dv.containsKey("name") ? dv.get("name").toString() : null;
                String ip = dv.containsKey("ip") ? dv.get("ip").toString() : null;
                String type = dv.containsKey("type") ? dv.get("type").toString() : null;
                String laneName = dv.containsKey("laneName") ? dv.get("laneName").toString() : null;

				if(StringUtils.isBlank(deviceName)){
					continue;
				}
				if(StringUtils.isBlank(type)){
					return new ServiceResponse(ResponseCode.MISS_REQUIRED);
				}
				if(ip.length() > 20){
					continue;
				}

				Device device = null;
				Lane lane = null;

				//闸机 摄像枪 ETC 绑定车道
				if(!type.equals("2") && !type.equals("4")){
					lane = laneService.findByParkIdAndLaneName(parkId, laneName);
					if (lane == null){
						//不存在时，创建车道保存车道
						lane = new Lane();
						lane.setName(laneName);
						lane.setIp(ip);
						lane.setParkId(parkId);
						lane.setDeleted(false);

						lane.setCreatedDate(new Date());
						lane.setUpdatedDate(lane.getCreatedDate());
						laneService.save(lane);
					}
					//给车道赋予摄像枪ip,修改车道信息持久化
					if (StringUtils.isNotBlank(type) && (Byte.parseByte(type) == (byte)Device.DeviceType.CAMERA.getValue())){
						lane.setIp(ip);
						laneService.update(lane);
					}
					device = deviceService.findByIpAndLaneId(ip,lane.getId(),type);
				}else{
					//系统或Agent 不绑定车道
					device = deviceService.findByParkIdAndIpAndType(parkId,ip,Integer.valueOf(type.toString()));
					if(ip.equals("127.0.0.1")){
						continue;
					}
				}

                if (device == null){
                    device = new Device();
                    device.setIp(ip);
                    device.setName(deviceName);
                    device.setCreatedDate(new Date());
                    device.setUpdatedDate(new Date());
                    device.setType(Byte.parseByte(type));
                    if(!type.equals("2") && !type.equals("4")){
						device.setLaneId(lane.getId());
					}
                    device.setStatus((byte) Device.DeviceStatus.NORMAL.getValue());
                    device.setDeleted(false);
                    device.setParkId(parkId);
                    deviceService.save(device);
                }else{
                	if(device.getParkId() == 0){
                		device.setParkId(parkId);
						deviceService.update(device);
                	}
				}
            }
        } catch (Exception e) {
			logger.error("DeviceController.post",e);
            throw new ServiceException(e);
        }
        return new ServiceResponse(ResponseCode.SUCCESS);
    }
}
