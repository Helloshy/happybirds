package com.airparking.apms.api.account.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.account.entity.OfflineAccount;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.lang.Long;
import java.util.Date;
import java.util.List;

/**
 * Created by ryan on 2016-07-27.
 */
public interface OfflineAccountMapper extends AbstractMapper<OfflineAccount, Long> {
  @ResultMap("entityMap")
  @Select("select * from offline_account where park_id = #{parkId} and is_deleted is false and is_disabled is false")
  List<OfflineAccount> findOfflineAccount(Long parkId);

  List<OfflineAccount> findByToday(@Param("parkId") Long parkId, @Param("start") Date start,
      @Param("end") Date end);
  
  OfflineAccount findByUsername(@Param("username") String username, @Param("parkId") Long parkId);
}