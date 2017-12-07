package com.kapphk.web.service.inter.imethod.community;

import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanCommunityService extends BaseService<BeanCommunity, Long> {

	/**
	 * 社区列表
	 * @author zoneyu 16-11-3
	 */
	public Result searchCommunityList(Long uid ,String date, Result rs) throws Exception ;

	/**
	 * 校验是否能进入社区
	 * @author zoneyu 16-11-4
	 */
	public Result checkUserInfo(Long uid, Long communityId, Result rs) throws Exception ;

	/**
	 * 保存社区信息
	 * @author zoneyu 16-11-4
	 */
	public Result saveCommunityInfo(BeanCommunity bean, Result rs) throws Exception ;

	/**
	 * 社区成员
	 * @author zoneyu 16-11-4
	 */
	public Result searchCommunityMembers(BeanCommunity bean, Result rs) throws Exception ;

	/**
	 * 未加入的社区
	 * @author zoneyu 16-11-5
	 */
	public Result searchCommunityListByProvince(String province ,Long uid , Integer page, Result rs) throws Exception ;

	/**
	 * 社区红点
	 * @author zoneyu 16-12-24
	 */
	public Result getPoint(Long communityId ,Long uid, String date ,Integer position, Result rs) throws Exception ;

}
