package com.airparking.apms.api.version.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.airparking.apms.api.version.entity.Version;
import com.airparking.core.base.mapper.AbstractMapper;

/**
 * Created by ryan on 2016-08-05.
 */
public interface VersionMapper extends AbstractMapper<Version, Long> {
  Map<String, Object> find(@Param("appid") String appid);

  void updateStatus(@Param("isUpdated") int isUpdated, @Param("id") Long id);
}