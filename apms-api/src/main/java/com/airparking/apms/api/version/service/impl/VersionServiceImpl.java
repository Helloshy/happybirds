package com.airparking.apms.api.version.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.version.mapper.VersionMapper;
import com.airparking.apms.api.version.service.VersionService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-08-05.
 */
@Service("versionService")
public class VersionServiceImpl extends AbstractService implements VersionService {
  @Autowired
  private VersionMapper versionMapper;

  @Override
  public Map<String, Object> find(String appid) {
    return versionMapper.find(appid);
  }

  @Override
  public void updateStatus(int isUpdated, Long id) {
    versionMapper.updateStatus(isUpdated, id);
  }

}