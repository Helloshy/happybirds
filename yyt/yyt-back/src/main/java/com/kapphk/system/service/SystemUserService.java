package com.kapphk.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.Company;

public interface SystemUserService extends BaseService<SystemUser, Long> {
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	public SystemUser findByUsername(String username);
	/**
	 * 查询
	 * @author zoneyu 16-6-3
	 */
	public Result searchList(SystemUser bean, int page, int rows, Result rs) throws Exception ;

	/**
	 * 新增|修改
	 * @author zoneyu 16-6-3
	 */
	public Result saveData(SystemUser bean, Result rs,Long RoleId) throws Exception ;

	/**
	 * 修改
	 * @author zoneyu 16-6-3
	 */
	public Result getData(Long id, Result rs) throws Exception ;

	/**
	 * 删除
	 * @author zoneyu 16-6-3
	 */
	public Result delete(String ids, Result rs) throws Exception ;

	/**
	 * 后台页面登录
	 * @author zoneyu 16-6-3
	 */
	public Result sign(SystemUser bean, HttpServletRequest request,
			Result rs) throws Exception ;
	
	
	public Map<String,Object> findUserAndUserRole(Long id);
	
	
	public Result editPwd(String pwd,String newPwd,String reNewPwd,Result result);
	
	public Result updateState(String ids, Result rs) throws Exception ;
	
}


  