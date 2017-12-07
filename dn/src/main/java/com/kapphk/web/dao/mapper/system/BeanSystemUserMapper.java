package com.kapphk.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.system.BeanSystemUser;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-05-23
*/
public interface BeanSystemUserMapper extends BaseMapper<BeanSystemUser, Long> {
	
	/**
	 * 查询管理员列表
	 * @author dengwen 
	 * 2016-9-14下午1:48:28
	 */
	public List<Map<String,Object>> findList(@Param("page")int page, @Param("rows")int rows);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-14下午2:26:09
	 */
	public Map<String,Object> findDetail(@Param("id")Long id);
}