package com.kapphk.system.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.Message;

/**
 * 消息服务数据接口
 * @author Administrator
 *
 */
public interface MessageService extends BaseService<Message,Long>{

	PageInfo<Message> findPageByParam(Message message, Integer page, Integer rows, String sort);
}
