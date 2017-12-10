package com.airparking.apms.api.bwlist.mapper;

import com.airparking.core.base.mapper.AbstractMapper;
import com.airparking.apms.api.bwlist.entity.ParkBWList;

import java.lang.Long;
import java.util.List;

/**
 * Created by ryan on 2016-07-26.
 */
public interface ParkBWListMapper extends AbstractMapper<ParkBWList, Long> {
    int saveOrUpdate(List<ParkBWList> parkBWLists);
}