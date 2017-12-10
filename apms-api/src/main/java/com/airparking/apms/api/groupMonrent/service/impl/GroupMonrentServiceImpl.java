package com.airparking.apms.api.groupMonrent.service.impl;

import com.airparking.apms.api.groupMonrent.entity.GroupMonrent;
import com.airparking.apms.api.groupMonrent.service.GroupMorentService;
import com.airparking.apms.api.groupMonrent.mapper.GroupMorentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/14.
 */
@Service("groupMonrentService")
public class GroupMonrentServiceImpl implements GroupMorentService{

    @Autowired
    private GroupMorentMapper groupMorentMapper;
    @Override
    public void save(GroupMonrent groupMonrent) {
        groupMorentMapper.add(groupMonrent);
    }

    @Override
    public GroupMonrent findByGmrid(String gmrid) {
        return groupMorentMapper.findByGmrid(gmrid);
    }

    @Override
    public void update(GroupMonrent groupMonrent) {
        groupMorentMapper.update(groupMonrent);
    }
}
