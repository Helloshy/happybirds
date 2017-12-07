package com.kapphk.system.mapper;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.entity.PermissionEntity;
import com.kapphk.system.bean.Role;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 数据操作接口
 * @author zoneyu 2016-05-23
*/
public interface RoleMapper extends BaseMapper<Role, Long> {

	public List<Role> findRoleByUserId(Long userId);

	public List<PermissionEntity> findListByParam(@Param("param")Map<String, Object> param);

}