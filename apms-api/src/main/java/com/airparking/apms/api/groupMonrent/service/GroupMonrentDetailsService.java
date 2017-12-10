package com.airparking.apms.api.groupMonrent.service;

import com.airparking.apms.api.groupMonrent.entity.GroupMonrentDetails;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface GroupMonrentDetailsService {

    void save(GroupMonrentDetails groupMonrentDetails);

    void deleteDetailsByGmId(Long groupMonrentId);

    List<GroupMonrentDetails> findByGmId(Long id);

}
