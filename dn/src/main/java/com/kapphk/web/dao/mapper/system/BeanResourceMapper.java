package com.kapphk.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.system.BeanResource;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 数据操作接口
 * @author zoneyu 2016-05-23
*/
public interface BeanResourceMapper extends BaseMapper<BeanResource, Long> {

	/**
	 * 获取当前资源以及父资源id
	 * @author dengwen 
	 * 2016-9-5上午11:10:14
	 */
	public List<Long> findResourceAll(@Param("ids")List<Long> resourceId);

	/**
	 * 根据当前登录用户id
	 * @author dengwen 
	 * 2016-9-16下午1:48:04
	 */
	public Map<String,Object> findRoleByUserId(@Param(value="userId")Long userId);

	/**
	 * 获取当前角色拥有的资源
	 * @author dengwen 
	 * 2016-9-5下午2:37:44
	 */
	public List<BeanResource> findResourcesById(@Param("ids")List<Long> ids);

	/**
	 * 根据角色获取资源
	 * @author dengwen 
	 * 2016-9-5下午2:47:11
	 */
	public List<Long> findResourceByRoleId(@Param("roleId")Long roleId);

}