package com.airparking.apms.api.bwlist.service.impl;

import com.airparking.apms.api.bwlist.entity.ParkBWList;
import com.airparking.core.base.service.AbstractService;
import com.airparking.apms.api.bwlist.mapper.ParkBWListMapper;
import com.airparking.apms.api.bwlist.service.ParkBWListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ryan on 2016-07-26.
 */
@Service("parkBWListService")
public class ParkBWListServiceImpl extends AbstractService implements ParkBWListService {
    @Autowired
    private ParkBWListMapper parkBWListMapper;

    @Override
    public int saveOrUpdate(List<ParkBWList> parkBWLists) {
        return parkBWListMapper.saveOrUpdate(parkBWLists);
    }
}