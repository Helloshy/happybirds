package com.airparking.apms.api.device.controller;

import com.airparking.apms.api.device.entity.Device;
import com.airparking.apms.api.device.entity.DeviceMsg;
import com.airparking.apms.api.device.service.DeviceMsgService;
import com.airparking.apms.api.device.service.DeviceService;
import com.airparking.apms.api.lane.entity.Lane;
import com.airparking.apms.api.lane.service.LaneService;
import com.airparking.apms.comm.DateFormats;
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
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * @create 2017-07-25-10:55
 */

@Controller("errorMsg")
public class ErrorMsgController extends AbstractController {

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceMsgService deviceMsgService;

	private static final Logger logger = LoggerFactory.getLogger(ErrorMsgController.class);

	@RequiredParams(params = {"data"})
	@Path("save")
	@Authorize(login = false)
	public ServiceResponse save(ServiceRequest request){
		Long parkId = request.getLong("parkId");
		String data = request.getString("data");
		try {
			List<Map<String, Object>> list = JsonUtils.toObject(data, List.class);
			for (Map deviceMsg :list){
				String flags = deviceMsg.containsKey("flags") ? deviceMsg.get("flags")+"" : null;
				String type = deviceMsg.containsKey("type") ? deviceMsg.get("type")+"" : null;
				String ip = deviceMsg.containsKey("ip") ? deviceMsg.get("ip")+"" : null;
				String deviceName = deviceMsg.containsKey("device_name") ? deviceMsg.get("device_name")+"" : null;
				String msg = deviceMsg.containsKey("msg") ? deviceMsg.get("msg")+"" : null;
				String createdDate = deviceMsg.containsKey("createdDate") ? deviceMsg.get("createdDate")+"" : null;

				if(StringUtils.isBlank(flags) || StringUtils.isBlank(type) || StringUtils.isBlank(ip) || StringUtils.isBlank(deviceName)){
					logger.error("miss required parameters->"+" flags:"+flags+" type:"+type+" ip:"+ip+" device_name:"+deviceName);
					return new ServiceResponse(ResponseCode.MISS_REQUIRED);
				}

				Long deviceId = deviceService.findByDeviceId(deviceName,parkId,ip);

				if(deviceId == null){
					logger.error("not have this device------->"+" deviceName:"+deviceName+" parkId:"+parkId+" ip:"+ip );
					continue;
					//return new ServiceResponse(ResponseCode.MISS_REQUIRED);
				}

				Device device = deviceService.findById(deviceId);

				if(flags.equals("1")){
					device.setStatus((byte)1);
				}else if(flags.equals("0")){
					device.setStatus((byte)0);
				}
				deviceService.update(device);

				DeviceMsg dmsg = new DeviceMsg();
				dmsg.setType(Byte.valueOf(type));
				dmsg.setParkId(parkId);
				dmsg.setFlags(flags.equals("0") ? false :true);
				dmsg.setIp(ip);
				dmsg.setDeviceId(deviceId);
				dmsg.setMsg(msg);
				dmsg.setCreatedDate(DateFormats.parseDateTimeDispalyFormat(createdDate));
				deviceMsgService.save(dmsg);
			}
		}catch (Exception e){
			logger.error("ErrorMsgController.save",e);
			throw new ServiceException(e);
		}
		return new ServiceResponse(ResponseCode.SUCCESS);
	}
}
