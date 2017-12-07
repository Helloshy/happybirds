package com.kapphk.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.system.bean.User;

import java.util.List;
import java.util.Map;

/**
 * 用户ID的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface UserMapper extends BaseMapper<User, Long> {


    public List<Map<String,Object>> findListByParam(@Param("param")Map<String, Object> param);



    void login(@Param("param")User user);
	
	
}