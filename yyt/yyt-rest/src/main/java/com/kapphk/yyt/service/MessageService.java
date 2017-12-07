package com.kapphk.yyt.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseService;
import com.kapphk.system.bean.Message;
import com.kapphk.web.utils.Result;

public interface MessageService  extends BaseService<Message, Long>{

	/**
	 * 根据用户id获取推送消息
	 * @param uid 用户id
	 * @return
	 */
	PageInfo<Message> findPageByUid(Long uid,Integer isRead,Integer page,Integer limit);

	/**
	 * 更新消息为已读状态
	 * @param uid 用户id
	 * @param messageid 消息id
 	 * @param isread 消息状态
	 * @param result
	 * @return
	 */
	Result updateState(Long uid, Long messageid, Integer isread, Result result);

}
