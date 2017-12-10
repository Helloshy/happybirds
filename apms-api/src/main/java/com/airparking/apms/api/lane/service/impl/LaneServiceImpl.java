package com.airparking.apms.api.lane.service.impl;

import com.airparking.apms.api.lane.entity.Lane;
import com.airparking.apms.api.lane.mapper.LaneMapper;
import com.airparking.apms.api.lane.service.LaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
@Service("laneService")
public class LaneServiceImpl implements LaneService {

    @Autowired
    private LaneMapper laneMapper;

    @Override
    public void save(Lane lane) {
        laneMapper.add(lane);
    }

    @Override
    public Lane findByParkIdAndLaneName(Long parkId, String laneName) {
        List<Lane> list = laneMapper.findByParkIdAndName(parkId, laneName);
        return (list != null && list.size()>0)?list.get(0):null;
    }

    @Override
    public Lane findByIp(String ip) {
        List<Lane> list = laneMapper.findByIp(ip);
        return (list != null && list.size()>0)?list.get(0):null;
    }

    @Override
    public void update(Lane lane) {
        laneMapper.update(lane);
    }
}
