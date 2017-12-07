package com.kapphk.web.service.inter.imethod.community;

import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserCommunityService extends BaseService<BeanUserCommunity, Long> {

	/**
	 * 社区信息
	 * @author zoneyu 16-11-2
	 */
	public Result searchCommunityInfo(Long id, Result rs) throws Exception ;

	/**
	 * 社区公告、动态
	 * @author zoneyu 16-11-2
	 */
	public Result searchNoticeOrDynamic(Long uid, Long id, Integer type, Integer page, Result rs) throws Exception ;

	/**
	 * 社区个数
	 * @author zoneyu 16-11-2
	 */
	public Result getCommunityCounts(Long uid, Result rs) throws Exception ;

	/**
	 * 加入社区
	 * @author zoneyu 16-11-5
	 */
	public Result saveUserCommunity(BeanUserCommunity bean, Result rs) throws Exception ;

}
