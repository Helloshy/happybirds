package com.kapphk.system.service;

import java.util.List;
import java.util.Map;

import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.Role;
import com.kapphk.web.utils.Result;

public interface RoleService extends BaseService<Role, Long> {
	/**
	 * 根据用户id查询所属角色
	 * @param userId
	 * @return
	 */
	public List<Role> findRoleByUserId(Long userId);

	/**
	 * 查询
	 * @author zoneyu 16-6-3
	 */
	public Result getList(Role bean, int page, int rows, Result rs) throws Exception ;

	/**
	 * 新增|修改
	 * @author zoneyu 16-6-3
	 */
	public Result saveData(Role bean, Result rs) throws Exception ;

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
	 * 保存角色授权
	 * @author cgj 15-12-17
	 */
	public Result saveGrant(Long id, String menu, Result rs) throws Exception ;

	/**
	 * 查询角色授权
	 * @author cgj 15-12-17
	 */
	public List<Map<String,Object>> searchGrant(Long id,List<Map<String,Object>> list) throws Exception ;
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> findAll(Role role);

}


  