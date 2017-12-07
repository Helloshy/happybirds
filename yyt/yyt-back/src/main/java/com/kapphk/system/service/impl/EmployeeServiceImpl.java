package com.kapphk.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.entity.EmployeeEntity;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.bean.SystemUserRole;
import com.kapphk.system.mapper.SystemUserMapper;
import com.kapphk.system.mapper.SystemUserRoleMapper;
import com.kapphk.system.service.EmployeeService;
import com.kapphk.web.utils.PasswordHelper;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.bean.UserCompanyHall;
import com.kapphk.yyt.mapper.UserCompanyHallMapper;

/**
 * 员工管理服务层
 * @author Administrator
 *
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<SystemUser, Long> implements EmployeeService {

	@Autowired
	private SystemUserMapper mapper;
	@Autowired
	private SystemUserRoleMapper systemUserRoleMapper;
	@Autowired
	private UserCompanyHallMapper userCompanyHallMapper;
	
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}
	

	@Override
	public PageInfo<EmployeeEntity> queryEmployeeByPage(SystemUser systemUser, Integer page, Integer rows, String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<EmployeeEntity> resultMaps = mapper.queryEmployeeByPage(systemUser);
        PageInfo<EmployeeEntity> pageInfo = new PageInfo<EmployeeEntity>(resultMaps);
        return pageInfo;
	}


	@Override
	public List<SystemUser> finByCompanyId(Long companyId) {
		SystemUser user = new SystemUser();
		user.setCompanyId(companyId);
		user.setState(1);
		return mapper.findAll(user);
	}


	
	@Override
	public void saveEmployee(SystemUser user, Long companyHallId, Long roleId) {
		Date date = new Date();
		SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
		user.setUpdateBy(systemUser.getId());
		user.setUpdateTime(date);
		if(ValidateUtils.isBlank(user.getId())){
			//保存账号信息
			user.setCreateTime(date);
			user.setPwd("123456");//设置默认登录密码
			PasswordHelper helper = new PasswordHelper();
			helper.encryptPassword(user);
			mapper.insert(user);
			
		}else{
			//修改账号信息
			mapper.update(user);
			//删除角色关联信息
			SystemUserRole role = new SystemUserRole();
			role.setUserId(user.getId());
			systemUserRoleMapper.deleteByEntity(role);
			//删除营业厅关联信息
			UserCompanyHall hall = new UserCompanyHall();
			hall.setSid(user.getId());
			userCompanyHallMapper.deleteByEntity(hall);
		}
		//为员工账号分配角色
		SystemUserRole role = new SystemUserRole();
		role.setCreateTime(date);
		role.setRoleId(roleId);
		role.setUserId(user.getId());
		systemUserRoleMapper.insert(role);
		//关联营业厅
		UserCompanyHall hall = new UserCompanyHall();
		hall.setCreateTime(date);
		hall.setCompanyHallId(companyHallId);
		hall.setSid(user.getId());
		userCompanyHallMapper.insert(hall);
		
	}


	@Override
	public List<SystemUserRole> finByRoleId(Long roleId) {
		SystemUserRole systemUserRole  = new SystemUserRole();
		systemUserRole.setRoleId(roleId);
		return systemUserRoleMapper.findAll(systemUserRole);
	}
}
