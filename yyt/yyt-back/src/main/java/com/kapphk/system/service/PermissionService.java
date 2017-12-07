package com.kapphk.system.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.entity.PermissionEntity;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.Role;
import com.kapphk.system.bean.RoleResource;

public interface PermissionService extends BaseService<Role, Long> {

	PageInfo<PermissionEntity> findPermissionByPage(Map<String,Object> param ,Integer page, Integer rows, String sort);
	/**
	 * 获取权限页面
	 * @return
	 */
	List<Resource> getResource();
	/**
	 * 保存权限信息
	 * @param id
	 * @param uid
	 * @param roleName
	 * @param resourceId
	 */
	void save(Long id, String uid, String roleName, String resourceId);
	
	/**
	 * 获取权限页面
	 * @return
	 */
	List<RoleResource> getResourceIdByRoleId(Long RoleId);

	
}


  