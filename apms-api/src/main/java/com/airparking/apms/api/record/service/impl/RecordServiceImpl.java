package com.airparking.apms.api.record.service.impl;

import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.record.entity.Record;
import com.airparking.apms.api.record.mapper.RecordMapper;
import com.airparking.apms.api.record.service.RecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 2016-07-27.
 */
@Service("recordService")
public class RecordServiceImpl extends AbstractService implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void save(Record record) {
      recordMapper.add(record);
    }

}