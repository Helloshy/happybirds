package com.airparking.apms.api.device.controller;

import com.airparking.apms.api.device.entity.Device;
import com.airparking.apms.api.device.entity.PcInfo;
import com.airparking.apms.api.device.service.DeviceMsgService;
import com.airparking.apms.api.device.service.DeviceService;
import com.airparking.apms.api.device.service.PcInfoService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @create 2017-07-25-10:41
 * 系统硬件信息
 */

@Controller(value = "pcInfo")
public class PcInfoController extends AbstractController {

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private PcInfoService pcInfoService;

	private static final Logger logger =  LoggerFactory.getLogger(PcInfoController.class);

	@RequiredParams(params = {"data"})
	@Path(value = "save")
	@Authorize(login = false)
	public ServiceResponse save(ServiceRequest request){
		Long parkId = request.getLong("parkId");
		String data = request.getString("data");
		try {
			List<Map<String, Object>> list = JsonUtils.toObject(data, List.class);
			for (Map map : list) {
				String querySpeed = map.containsKey("query_speed") ? map.get("query_speed").toString() : "0";
				String deriveSpeed = map.containsKey("derive_speed") ? map.get("derive_speed").toString() : "0";
				String cpu_utilization = map.containsKey("cpu_utilization") ? map.get("cpu_utilization").toString() : "0";
				String cpu_temperature = map.containsKey("cpu_temperature") ? map.get("cpu_temperature").toString() : "0";
				String memory_usage = map.containsKey("memory_usage") ? map.get("memory_usage").toString() : "0";
				String hard_disk_usage = map.containsKey("hard_disk_usage") ? map.get("hard_disk_usage").toString() : "0";

				//获取一 个pc设备
				Device device = deviceService.findByParkIdAndType(parkId,Device.DeviceType.SYSTEM.getValue());
				if(device == null){
					return new ServiceResponse(ResponseCode.REQUEST_ERROR,"not have this device");
				}

				PcInfo pcInfo = pcInfoService.findByParkId(parkId);
				if(pcInfo == null){
					pcInfo = new PcInfo();
					pcInfo.setCreatedDate(new Date());
					pcInfo.setDeviceId(device.getId());//多台电脑
				}
				pcInfo.setQuerySpeed(Integer.valueOf(querySpeed));
				pcInfo.setDeriveSpeed(Integer.valueOf(deriveSpeed));
				pcInfo.setCpuUtilization(Integer.valueOf(cpu_utilization));
				pcInfo.setCpuTemperature(Integer.valueOf(cpu_temperature));
				pcInfo.setMemoryUsage(Integer.valueOf(memory_usage));
				pcInfo.setHardDiskUsage(Integer.valueOf(hard_disk_usage));
				pcInfo.setDeleted(false);
				pcInfo.setUpdatedDate(new Date());
				pcInfo.setParkId(parkId);
				if(pcInfo.getId() == null){
					pcInfoService.save(pcInfo);
				}else{
					pcInfoService.update(pcInfo);
				}
			}
		}catch (Exception e){
			logger.error("PcInfoController.save",e);
			throw new ServiceException(e);
		}
		return new ServiceResponse(ResponseCode.SUCCESS);
	}
}
