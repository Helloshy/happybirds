package com.airparking.apms.api.carModel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.carModel.entity.CarModel;
import com.airparking.apms.api.carModel.service.CarModelService;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;

/**
 * Created by ryan on 2016-08-05.
 */
@Controller("carModel")
public class CarModelController extends AbstractController {
  @Autowired
  private CarModelService carModelService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  @Path("save")
  public ServiceResponse savePrice(ServiceRequest request) {
    String data = request.getString("data");
    Long parkId = request.getLong("parkId");

    try {
      if (StringUtils.isBlank(data)) {
        return new ServiceResponse(ResponseCode.MISS_REQUIRED);
      }

      List<Map<String, Object>> carModelList = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> cml : carModelList) {
        String name = (String) cml.get("name");
        Integer status = Integer.valueOf((String) cml.get("status"));// 0:新增
                                                                     // 1:删除
        String code = (String) cml.get("offlineCarId");
        Long carId = 0l;
        if (cml.get("onlineCarId") != null && cml.get("onlineCarId") != "") {
          carId = Long.valueOf((String) cml.get("onlineCarId"));
        }

        CarModel carModel = null;
        if (status == 0) {
          carModel = carModelService.findByCode(code);
          if (carModel == null) {
            carModel = new CarModel();
            carModel.setCreatedDate(new Date());
            carModel.setIsDisabled(false);
            carModel.setDeleted(false);
            carModel.setName(name);
            carModel.setParkId(parkId);
            carModelService.findMaxType(parkId);
            carModel.setIsSend(false);
            Byte type = 0;
            if ("小型车".equals(name)) {
              type = 0;
            } else if ("大型车".equals(name)) {
              type = 1;
            } else {
              type = (byte) (carModelService.findMaxType(parkId) + 1);
            }
            carModel.setType(type);
            carModel.setCode(code);
            carModelService.save(carModel);
		  }
        } else {
          if (StringUtils.isNotBlank(code)) {
            carModel = carModelService.findByCode(code);
          } else if (carId != 0) {
            carModel = carModelService.findById(carId);
          }
          if (carModel != null) {
            carModel.setIsSend(true);
            carModel.setDeleted(true);
            carModel.setIsDisabled(true);
            carModel.setUpdatedDate(new Date());
            carModelService.update(carModel);
          }
        }
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }

  @Authorize(login = false)
  @Path("back")
  public ServiceResponse back(ServiceRequest request) {
    Long parkId = request.getLong("parkId");
    Map<String, Object> map = null;
    List<Map<String, Object>> mapList = null;
    try {
      mapList = new ArrayList<>();
      List<CarModel> carList = carModelService.findAllWithoutCode(parkId);
      for (CarModel cm : carList) {
        int status = 0; // 0:可用 1：不可用
        map = new HashMap<String, Object>();
        if (cm.getDeleted()) {
          status = 1;
        } else {
          if (cm.getIsDisabled()) {
            status = 1;
          }
        }
        map.put("status", status);
        map.put("name", cm.getName());
        map.put("id", cm.getId());
        map.put("type", cm.getType());
        map.put("createdDate", cm.getCreatedDate());
        map.put("createdBy", cm.getCreatedBy());
        map.put("offlineId", cm.getCode() != null ? cm.getCode() : "");
        mapList.add(map);
        cm.setIsSend(true);
        carModelService.update(cm);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ServiceResponse(ResponseCode.SERVER_ERROR);
    }

    return new ServiceResponse(mapList);
  }
}