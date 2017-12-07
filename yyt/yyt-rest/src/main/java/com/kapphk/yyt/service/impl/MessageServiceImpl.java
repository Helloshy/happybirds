package com.kapphk.yyt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.system.bean.Message;
import com.kapphk.system.bean.MessageDetail;
import com.kapphk.system.mapper.MessageDetailMapper;
import com.kapphk.system.mapper.MessageMapper;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.service.MessageService;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message, Long> implements MessageService{

	@Autowired
	private MessageMapper mapper;
	@Autowired
	private MessageDetailMapper detailMapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}

	@Override
	public PageInfo<Message> findPageByUid(Long uid,Integer isRead,Integer page,Integer limit) {
		PageHelper.orderBy("create_time desc");
		PageHelper.startPage(page, limit);
		List<Message> numbers = mapper.findListByUid(uid,isRead);
		PageInfo<Message> pageInfo = new PageInfo<Message>(numbers);
		return pageInfo;
	}

	@Override
	public Result updateState(Long uid, Long messageid, Integer isread, Result result) {
		MessageDetail detail = new MessageDetail();
		detail.setMessageId(messageid);
		detail.setUid(uid);
		List<MessageDetail> details = detailMapper.findList(detail);
		if(null != details && details.size()== 1){
			detail = details.get(0);
			detail.setIsRead(1);
			detailMapper.update(detail);
		}
		return result;
	}
	
}
