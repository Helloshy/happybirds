package com.kapphk.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.system.bean.Resource;


/**
 * 数据操作接口
 * @author zoneyu 2016-05-23
*/
public interface ResourceMapper extends BaseMapper<Resource, Long> {

	/**
	 * 查询菜单目录
	 * @author zoneyu 16-6-3
	 */
	public List<Resource> findUserResourcesByUserId(@Param(value = "userId") Long userId);
	
	/**
	 * 根据roleId查询菜单
	 * @param roleId
	 * @return
	 */
	public List<Resource> findResourceByRoleId(Long roleId);
	
	/**
	 * 批量更新roleres
	 * @param list
	 * @param rs
	 * @return
	 */
	public Integer saveRoleResOfBatch(List<Map<String, Object>> list);
	
	
	public List<Resource> findByParentId(Long parentId);

	public List<Resource> findResource(@Param("param")Resource resource, @Param("roleId")Long roleId);


}