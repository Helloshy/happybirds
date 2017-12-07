package com.kapphk.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.entity.PermissionEntity;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.Role;
import com.kapphk.system.bean.RoleResource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.bean.SystemUserRole;
import com.kapphk.system.mapper.ResourceMapper;
import com.kapphk.system.mapper.RoleMapper;
import com.kapphk.system.mapper.RoleResourceMapper;
import com.kapphk.system.mapper.SystemUserRoleMapper;
import com.kapphk.system.service.PermissionService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ValidateUtils;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Role, Long> implements PermissionService{

	@Autowired
	private RoleMapper mapper;
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Autowired
	private SystemUserRoleMapper systemUserRoleMapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}
	@Override
	public PageInfo<PermissionEntity> findPermissionByPage(Map<String,Object> param ,Integer page, Integer rows, String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        //SystemUser user = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
        List<PermissionEntity> resultMaps = mapper.findListByParam(param);
        PageInfo<PermissionEntity> pageInfo = new PageInfo<PermissionEntity>(resultMaps);
        return pageInfo;
	}
	@Override
	public List<Resource> getResource() {
		Resource resource = new Resource();
		resource.setParentId(0L);
		List<Resource> rs =  resourceMapper.findResource(resource,null);
		return rs;
	}
	
	@Override
	public void save(Long id, String uid, String roleName, String resourceId) {
		SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
    	Long companyId = systemUser.getCompanyId();
    	if(systemUser.getCompanyId() == 1){
    		companyId = systemUser.getId();
    	}
		Date date = new Date();
		Role role = new Role();
		if(ValidateUtils.isBlank(id)){
			//新增角色数据
			role.setRoleName(roleName);
			role.setState(1);
			role.setCreateTime(date);
			role.setCompanyId(companyId);
			mapper.insert(role);
		}else{
			//修改角色数据
			role.setId(id);
			role.setRoleName(roleName);
			mapper.update(role);
			//删除角色的权限
			roleResourceMapper.deleteByRoleId(id);
			//删除用户的授权
			SystemUserRole systemUserRole = new SystemUserRole();
			systemUserRole.setRoleId(id);
			systemUserRoleMapper.deleteByEntity(systemUserRole);
		}
		
		//分配角色权限
		List<Long> resIds = DataUtils.string2List(resourceId);
		List<RoleResource> roleResources = new ArrayList<RoleResource>();
		for(Long resource:resIds){
			RoleResource  roleResource = new RoleResource();
			roleResource.setRoleId(role.getId());
			roleResource.setResourceId(resource);
			roleResources.add(roleResource);
		}
		roleResourceMapper.inserts(roleResources);
		//设置员工的角色
		List<Long> userIds = DataUtils.string2List(uid);
		List<SystemUserRole> users = new ArrayList<SystemUserRole>();
		for(Long userId:userIds){
			SystemUserRole systemUserRole = new SystemUserRole();
			systemUserRole.setRoleId(role.getId());
			systemUserRole.setCreateTime(date);
			systemUserRole.setUserId(userId);
			users.add(systemUserRole);
		}
		systemUserRoleMapper.inserts(users);
	}
	@Override
	public List<RoleResource> getResourceIdByRoleId(Long roleId) {
		RoleResource roleResource = new RoleResource();
		roleResource.setRoleId(roleId);
		return roleResourceMapper.findAll(roleResource);
	}
	
	
}


  