package com.kapphk.web.dao.mapper.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.community.BeanCommunityContent;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-31
*/
public interface BeanCommunityContentMapper extends BaseMapper<BeanCommunityContent, Long> {

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-11-2下午1:45:40
	 */
	List<Map<String, Object>> findList(@Param("param")BeanCommunityContent bean,
			@Param("itemName")String itemName, @Param("nickName")String nickName, 
			@Param("page")int page, @Param("rows")int rows);

	/**
	 * 查询列表总数
	 * @author dengwen 
	 * 2016-11-2下午1:45:48
	 */
	int findCount(@Param("param")BeanCommunityContent bean,
			@Param("itemName")String itemName, @Param("nickName")String nickName);

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-11-2下午2:23:50
	 */
	Map<String, Object> findDetailById(@Param("id")Long id);

	/**
	 * 热门动态
	 * @author zoneyu 16-11-2
	 */
	public List<Map<String, Object>> searchDynamicMax(@Param("uid")Long uid,@Param("communityId")Long communityId, 
			@Param("page")Integer page, @Param("rows")Integer rows);
	/**
	 * 公告&动态评论
	 * @author zoneyu 16-11-5
	 */
	public List<Map<String, Object>> searchCommunityContentList(@Param("id")Long id, 
			@Param("page")Integer page, @Param("rows")Integer rows);

	/**
	 * 查询当前用户动态发布数
	 * @author zoneyu 16-12-10
	 */
	public int searchCommunityCounts(@Param("uid")Long uid, @Param("localDate")String localDate);
	
}