package com.kapphk.system.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.entity.EmployeeEntity;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.bean.SystemUserRole;

public interface EmployeeService extends BaseService<SystemUser, Long> {
	
	public PageInfo<EmployeeEntity> queryEmployeeByPage(SystemUser systemUser, Integer page, Integer rows, String sort);

	/**
	 * 根据公司id获取人员信息
	 * @param companyId
	 * @return
	 */
	public List<SystemUser> finByCompanyId(Long companyId);
	/**
	 * 根据角色id获取人员信息
	 * @param companyId
	 * @return
	 */
	public List<SystemUserRole> finByRoleId(Long RoleId);

	/**
	 * 保存员工信息
	 * @param user
	 * @param companyHallId
	 * @param roleId
	 */
	public void saveEmployee(SystemUser user, Long companyHallId, Long roleId);
	
	
	
}


  