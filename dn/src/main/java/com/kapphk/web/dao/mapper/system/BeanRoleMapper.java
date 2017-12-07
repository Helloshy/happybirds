package com.kapphk.web.dao.mapper.system;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.system.BeanRole;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 数据操作接口
 * @author zoneyu 2016-05-23
*/
public interface BeanRoleMapper extends BaseMapper<BeanRole, Long> {
	/**
	 * 修改数据
	 */
	public Map<String,Object> findData(@Param("id")Long id);

	/**
	 * 查询上级
	 */
	public Map<String, Object> findParentResource(@Param("id")Long id);
}