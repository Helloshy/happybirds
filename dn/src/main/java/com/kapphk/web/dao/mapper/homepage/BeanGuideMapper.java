package com.kapphk.web.dao.mapper.homepage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.homepage.BeanGuide;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-12-22
*/
public interface BeanGuideMapper extends BaseMapper<BeanGuide, Long> {
	
	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-9-28上午11:38:14
	 */
	public List<Map<String, Object>> findList(@Param("param")BeanGuide bean, @Param("page")int page, int rows);

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-9-28下午1:41:02
	 */
	public int findCount(@Param("param")BeanGuide bean);

	/**
	 * 首页轮播图数据
	 * @param zoneyu 16-10-10
	 */
	public List<Map<String, Object>> getHomeGuideList();

	/**
	 * 动能集团轮播图
	 * @param zoneyu 16-12-22
	 */
	public List<BeanGuide> findAllSort(@Param("param")BeanGuide bean);
}