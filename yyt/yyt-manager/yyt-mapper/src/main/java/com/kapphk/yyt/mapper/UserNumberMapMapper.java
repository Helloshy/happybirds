package com.kapphk.yyt.mapper;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.yyt.bean.UserNumberMap;
import org.apache.ibatis.annotations.Param;

/**
 * 的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface UserNumberMapMapper extends BaseMapper<UserNumberMap, Long> {


    UserNumberMap findByUnid(@Param("unid") Long unid);


    UserNumberMap findUidByUnid(Long unid);
}