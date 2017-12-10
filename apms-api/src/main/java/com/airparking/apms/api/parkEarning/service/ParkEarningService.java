package com.airparking.apms.api.parkEarning.service;

import java.util.List;
import java.util.Map;

/**
 * Created by shanyong on 2016-12-14.
 */
public interface ParkEarningService {

  List findMonthrentRecByTenantId(Long tenantId, String start, String end);
  
  List<Map<String, Object>> findMonthrentParkAndTotal(Long tenantId, String start, String end);

  List<Map<String, Object>> findParkOrderByTenantId(Long tenantId, String start, String end);

  List<Map<String, Object>> findParkOrderAndTotal(Long tenantId, String start, String end);

  List<Map<String, Object>> findCodePay(String start, String end);

  List<Map<String, Object>> findCodePayOrder(String start, String dayend);

  List<Map<String,Object>> findTenantId();

  List<String> findTenantMail(Long tenantId);
}