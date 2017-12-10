package com.airparking.apms.api.order.service.impl;

import com.airparking.apms.api.order.entity.AbnormalProcess;
import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.order.mapper.AbnormalProcessMapper;
import com.airparking.apms.api.order.service.AbnormalProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-07-27.
 */
@Service("abnormalProcessService")
public class AbnormalProcessServiceImpl extends AbstractService implements AbnormalProcessService {
    @Autowired
    private AbnormalProcessMapper abnormalProcessMapper;

    @Override
    public void add(AbnormalProcess abnormalProcess) {
        abnormalProcessMapper.add(abnormalProcess);
    }
}