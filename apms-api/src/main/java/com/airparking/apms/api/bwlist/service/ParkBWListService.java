package com.airparking.apms.api.bwlist.service;

import com.airparking.apms.api.bwlist.entity.ParkBWList;

import java.util.List;

/**
 * Created by ryan on 2016-07-26.
 */
public interface ParkBWListService {
    int saveOrUpdate(List<ParkBWList> parkBWLists);
}