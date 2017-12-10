package com.airparking.apms.api.groupMonrent.mapper;

import com.airparking.apms.api.groupMonrent.entity.GroupMonrentDetails;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface GroupMonrentDetailsMapper extends AbstractMapper<GroupMonrentDetails, Long> {
    
    void deleteDetailsByGmId(@Param("groupMonrentId") Long groupMonrentId);

    List<GroupMonrentDetails> findByGmId(@Param("groupMonrentId") Long groupMonrentId);
}
