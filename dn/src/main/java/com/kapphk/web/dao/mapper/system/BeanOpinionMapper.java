package com.kapphk.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.system.BeanOpinion;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-09-20
*/
public interface BeanOpinionMapper extends BaseMapper<BeanOpinion, Long> {

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-9-20下午4:36:44
	 */
	public List<Map<String,Object>> findList(@Param("param")BeanOpinion bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-20下午4:51:32
	 */
	public Map<String,Object> findDetailById(@Param("id")Long id);

	public int findCount(@Param("param")BeanOpinion bean);
}