package com.kapphk.system.mapper;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.system.bean.RoleResource;

/**
 * 数据操作接口
 * @author zoneyu 2016-05-23
*/
public interface RoleResourceMapper extends BaseMapper<RoleResource, Long> {
	
	Integer deleteByRoleId(Long roleId);
}