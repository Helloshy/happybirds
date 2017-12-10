package com.airparking.apms.api.version.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.version.service.VersionService;
import com.airparking.apms.comm.DateFormats;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;

/**
 * Created by ryan on 2016-08-05.
 */
@Controller("version")
public class VersionController extends AbstractController {
  @Autowired
  private VersionService versionService;

  @RequiredParams(params = { "data" })
  @Authorize(login = false)
  public ServiceResponse update(ServiceRequest request) {
    String data = request.getString("data");

    try {
      if (StringUtils.isBlank(data)) {
        return new ServiceResponse(ResponseCode.MISS_REQUIRED);
      }

      List<Map<String, Object>> list = JsonUtils.toObject(data, List.class);
      for (Map<String, Object> l : list) {
        versionService.updateStatus((int) l.get("status"), (Long) l.get("id"));
      }

    } catch (Exception e) {
      e.printStackTrace();
      return new ServiceResponse(ResponseCode.SERVER_ERROR);
    }

    return new ServiceResponse(ResponseCode.SUCCESS);
  }

  @Authorize(login = false)
  public ServiceResponse back(ServiceRequest request) {
    String appId = request.getString("appId");
    Map<String, Object> response = null;
    Map<String, Object> data = null;
    try {
      response = new HashMap<>();
      data = versionService.find(appId);
      if (data != null) {
        response.put("id", data.get("id"));
        response.put("type", (int) data.get("type"));
        response.put("currentVersion", data.get("current_version"));
        response.put("currentVersionName", data.get("current_version_name"));
        response.put("newestVersion", data.get("newest_version"));
        response.put("newestVersionName", data.get("newest_version_name"));
        response.put("downloadLink", data.get("download_link"));
        response.put("versionUpdate",
            DateFormats.formatDateTimeDispaly((Date) data.get("version_update")));
        versionService.updateStatus(1, (Long) data.get("id"));
      }
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    return new ServiceResponse(response);
  }
}