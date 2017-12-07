package com.kapphk.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.system.BeanUserApply;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-09-21
*/
public interface BeanUserApplyMapper extends BaseMapper<BeanUserApply, Long> {

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-9-21下午2:16:09
	 */
	public List<Map<String, Object>> findList(@Param("param")BeanUserApply bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询总数
	 * @author dengwen 
	 * 2016-9-21下午2:16:22
	 */
	public int findCount(@Param("param")BeanUserApply bean);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-21下午2:29:51
	 */
	public Map<String, Object> findDetailById(@Param("id")Long id);
}