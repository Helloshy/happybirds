package com.airparking.apms.api.groupMonrent.service;

import com.airparking.apms.api.groupMonrent.entity.GroupMonrent;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface GroupMorentService {

    void save(GroupMonrent groupMonrent);

    GroupMonrent findByGmrid(String gmrid);

    void update(GroupMonrent groupMonrent);
}
