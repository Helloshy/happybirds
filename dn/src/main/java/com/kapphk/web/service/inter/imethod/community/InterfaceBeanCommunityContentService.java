package com.kapphk.web.service.inter.imethod.community;

import com.kapphk.web.bean.community.BeanCommunityContent;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanCommunityContentService extends BaseService<BeanCommunityContent, Long> {

	/**
	 * 热门动态
	 * @author zoneyu 16-11-2
	 */
	public Result searchDynamicMax(Long uid,Long communityId, Integer page, Result rs) throws Exception ;

	/**
	 * 公告&动态评论
	 * @author zoneyu 16-11-5
	 */
	public Result searchCommunityContentList(Long id, Integer page, Result rs) throws Exception ;

	/**
	 * 保存公告&动态
	 * @author zoneyu 16-11-5
	 */
	public Result saveCommunityContent(BeanCommunityContent bean, Result rs) throws Exception ;

	/**
	 * 帖子功能呢键
	 * @author zoneyu 16-11-10
	 */
	public Result operator(Long id, Long uid, Integer type, String content, Result rs) throws Exception ;

	/**
	 * 帖子功能键
	 * @author zoneyu 16-11-10
	 */
	public Result savePoint(Long id, Long uid, Integer type, Result rs) throws Exception ;
	
}
