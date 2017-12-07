package com.kapphk.web.dao.mapper.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-31
*/
public interface BeanUserCommunityMapper extends BaseMapper<BeanUserCommunity, Long> {

	/**
	 * 社区公告、动态
	 * @author zoneyu 16-11-2
	 */
	public List<Map<String, Object>> searchNoticeOrDynamic(@Param("uid")Long uid, @Param("id")Long id, 
			@Param("type")Integer type, @Param("page")Integer page, @Param("rows")Integer rows) throws Exception ;

	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-11-1下午5:56:23
	 */
	public List<Map<String, Object>> findList(@Param("itemName")String itemName, @Param("userName")String userName,
			@Param("realName")String realName, @Param("page")int page, @Param("rows")int rows);

	public int findCount(@Param("itemName")String itemName, @Param("userName")String userName,@Param("realName")String realName);

	/**
	 * 删除个人一级客户跟从的社区
	 * @author dengwen 
	 * 2016-12-9下午6:38:24
	 */
	public void deleteByuids(@Param("ids")List<Long> ids, @Param("uid")Long uid);

	/**
	 * 获取社区成员
	 * @author dengwen 
	 * 2016-12-24上午10:40:33
	 */
	public List<String> findByCommunityId(@Param("cid")List<Long> cid);
	
}