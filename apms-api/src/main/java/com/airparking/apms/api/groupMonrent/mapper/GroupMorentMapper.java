package com.airparking.apms.api.groupMonrent.mapper;

import com.airparking.apms.api.groupMonrent.entity.GroupMonrent;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface GroupMorentMapper extends AbstractMapper<GroupMonrent, Long> {

    GroupMonrent findByGmrid(@Param("gmrid") String gmrid);
}
