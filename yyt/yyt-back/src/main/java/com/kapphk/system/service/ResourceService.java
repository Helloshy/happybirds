package com.kapphk.system.service;

import java.util.List;
import java.util.Map;

import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.Resource;
import com.kapphk.web.utils.Result;



public interface ResourceService extends BaseService<Resource, Long> {
	
	/**
	 * 根据roleId查询菜单
	 * @param roleId
	 * @return
	 */
	public List<Resource> findResourceByRoleId(Long roleId);
	
	/**
	 * 查询菜单目录
	 * @author zoneyu 16-6-3
	 */
	public List<Resource> findUserResourcesByUserId(Long userId,List<Resource> list);
	
	/**
	 * 新增或修改
	 * @param bean
	 * @param rs
	 * @return
	 */
	public Result saveData(Resource bean, Result rs) ;

	/**
	 * 初始化菜单下拉框
	 * @author zoneyu 15-12-17
	 */
	public List<Map<String, Object>> searchResourceList(
			List<Map<String, Object>> list) ;
	
	/**
	 * 批量删除
	 * @param ids
	 * @param rs
	 * @return
	 */
	public Result delete(String ids, Result rs);
	
	/**
	 * 批量保存roleres
	 * @param list
	 * @param rs
	 * @return
	 */
	public Result saveRoleResOfBatch(Long roleId,String resourceIds, Result rs);
	
	public List<Resource> findByParentId(Long parentId);
	
}
