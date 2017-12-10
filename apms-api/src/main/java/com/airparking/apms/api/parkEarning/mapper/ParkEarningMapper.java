package com.airparking.apms.api.parkEarning.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * Created by shanyong on 2016-12-14.
 */
public interface ParkEarningMapper {

  List<Map<String, Object>> findMonthrentRecByTenantId(@Param("tenantId")Long tenantId, 
		  @Param("start")String start, @Param("end")String end);

  List<Map<String, Object>> findMonthrentParkAndTotal(@Param("tenantId")Long tenantId, 
		  @Param("start")String start, @Param("end")String end);

  List<Map<String, Object>> findParkOrderByTenantId(@Param("tenantId")Long tenantId, 
		  @Param("start")String start, @Param("end")String end); 

  List<Map<String, Object>> findParkOrderAndTotal(@Param("tenantId")Long tenantId, 
		  @Param("start")String start, @Param("end")String end);

  List<Map<String, Object>> findCodePay(@Param("start")String start, @Param("end")String end);

  List<Map<String, Object>> findCodePayOrder(@Param("start")String start, @Param("end")String end);

  List<Map<String,Object>> findTenantId();
  
  List<String> findTenantMail(Long tenantId);
  
}