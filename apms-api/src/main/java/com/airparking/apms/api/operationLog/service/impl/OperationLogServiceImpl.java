package com.airparking.apms.api.operationLog.service.impl;

import com.airparking.apms.api.operationLog.entity.OperationLog;
import com.airparking.apms.api.operationLog.mapper.OperationLogMapper;
import com.airparking.apms.api.operationLog.service.OperationLogService;
import com.airparking.core.base.service.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-08-03.
 */
@Service("operationLogService")
public class OperationLogServiceImpl extends AbstractService implements OperationLogService {
    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public void save(OperationLog operationLog) {
      operationLogMapper.add(operationLog);
    }

    @Override
    public void update(OperationLog operationLog) {
      operationLogMapper.update(operationLog);
    }

}