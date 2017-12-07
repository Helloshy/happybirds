package com.kapphk.web.service.inter.imethod.homepage;

import com.kapphk.web.bean.system.BeanMessagePush;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanMessagePushService {

	/**
	 * 系统消息列表
	 * @author dengwen 
	 * 2016-11-12下午3:34:19
	 */
	public Result getMessagePushList(Long uid, Integer page, Result rs) throws Exception;

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-11-12下午3:36:04
	 */
	public BeanMessagePush getMessagePushDetail(Long id);

	/**
	 * 消息红点
	 * @author zoneyu 16-12-3
	 */
	public Result getAmount(String time, Result rs) throws Exception;

}
