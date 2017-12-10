package com.airparking.apms.api.version.service;

import java.util.Map;

/**
 * Created by ryan on 2016-08-05.
 */
public interface VersionService {
  Map<String, Object> find(String appid);

  void updateStatus(int isUpdated, Long id);
}