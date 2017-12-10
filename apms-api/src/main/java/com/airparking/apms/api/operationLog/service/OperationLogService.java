package com.airparking.apms.api.operationLog.service;

import com.airparking.apms.api.operationLog.entity.OperationLog;

/**
 * Created by ryan on 2016-08-03.
 */
public interface OperationLogService {
  void save(OperationLog operationLog);
  void update(OperationLog operationLog);
}