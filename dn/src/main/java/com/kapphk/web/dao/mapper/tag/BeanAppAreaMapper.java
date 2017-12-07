package com.kapphk.web.dao.mapper.tag;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.tag.BeanAppArea;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-28
*/
public interface BeanAppAreaMapper extends BaseMapper<BeanAppArea, String> {

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-10-28下午4:25:02
	 */
	Map<String, Object> findDetailById(@Param("id")String id);

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-10-28下午4:25:08
	 */
	List<Map<String, Object>> findList(@Param("page")int page, @Param("rows")int rows);

	/**
	 * 获取全部区域
	 * @author dengwen 
	 * 2016-11-2上午11:40:54
	 */
	List<Map<String, Object>> findAreaList();
}