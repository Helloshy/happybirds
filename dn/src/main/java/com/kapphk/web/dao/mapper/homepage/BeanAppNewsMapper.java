package com.kapphk.web.dao.mapper.homepage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.homepage.BeanAppNews;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键数据操作接口
 * @author zoneyu 2016-12-22
*/
public interface BeanAppNewsMapper extends BaseMapper<BeanAppNews, Long> {
	
	/**
	 * 更新之前置顶
	 * @author dengwen 
	 * 2016-9-26上午9:17:13
	 */
	public void upIsTop(@Param("param")BeanAppNews bean);

	/** 
	 * 获取列表
	 * @author dengwen 
	 * 2016-9-30下午2:40:23
	 */
	public List<BeanAppNews> findList(@Param("param")BeanAppNews bean, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 获取列表总数
	 * @author dengwen 
	 * 2016-9-30下午2:40:28
	 */
	public int findCount(@Param("param")BeanAppNews bean);

	/**
	 * 资讯列表
	 * @author zoneyu 16-10-9
	 */
	public List<Map<String, Object>> getNewsList(@Param("newsTag")String newsTag, @Param("uid")Long uid,
			@Param("recordType")Integer recordType,
			@Param("page")int page, @Param("rows")int rows);

	/**
	 * 首页动能资讯
	 * @author zoneyu 16-10-10
	 */
	public List<Map<String, Object>> findHomeData(@Param("uid")Long uid);

	/**
	 * 欧巴资讯
	 * @author zoneyu 16-10-11
	 */
	public List<Map<String, Object>> getList(@Param("recordType")String recordType, @Param("uid")Long uid,
			@Param("page")int page, @Param("rows")int rows);

	/**
	 * 公司公告列表
	 * @author dengwen 
	 * 2016-11-12下午2:50:47
	 */
	public List<Map<String, Object>> getNoticeList(@Param("newsTag")String newsTag, @Param("page")int page, @Param("rows")int rows);

	/**
	 * 资讯赞赏记录
	 * @author dengwen 16-11-15
	 */
	public List<Map<String, Object>> getRecord(@Param("id")Long id, @Param("uid")Long uid);
}