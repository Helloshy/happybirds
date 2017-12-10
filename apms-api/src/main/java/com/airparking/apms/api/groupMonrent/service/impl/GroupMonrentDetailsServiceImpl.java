package com.airparking.apms.api.groupMonrent.service.impl;

import com.airparking.apms.api.groupMonrent.entity.GroupMonrentDetails;
import com.airparking.apms.api.groupMonrent.mapper.GroupMonrentDetailsMapper;
import com.airparking.apms.api.groupMonrent.service.GroupMonrentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
@Service("groupMonrentDetailsService")
public class GroupMonrentDetailsServiceImpl implements GroupMonrentDetailsService {

    @Autowired
    private GroupMonrentDetailsMapper groupMonrentDetailsMapper;

    @Override
    public void save(GroupMonrentDetails groupMonrentDetails) {
        groupMonrentDetailsMapper.add(groupMonrentDetails);
    }

    @Override
    public void deleteDetailsByGmId(Long groupMonrentId) {
        groupMonrentDetailsMapper.deleteDetailsByGmId(groupMonrentId);
    }

    @Override
    public List<GroupMonrentDetails> findByGmId(Long gm_id) {
        return groupMonrentDetailsMapper.findByGmId(gm_id);
    }
}
