package com.airparking.apms.api.lane.service;

import com.airparking.apms.api.lane.entity.Lane;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface LaneService {
    void save(Lane lane);

    Lane findByParkIdAndLaneName(Long parkId, String laneName);

    Lane findByIp(String ip);

    void update(Lane lane);
}
