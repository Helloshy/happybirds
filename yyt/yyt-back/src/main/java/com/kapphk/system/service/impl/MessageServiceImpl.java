package com.kapphk.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.Message;
import com.kapphk.system.mapper.MessageMapper;
import com.kapphk.system.service.MessageService;
import com.kapphk.yyt.bean.Community;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<Message,Long> implements MessageService {

    @Autowired
    private MessageMapper mapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

	@Override
	public PageInfo<Message> findPageByParam(Message message, Integer page, Integer rows, String sort) {
		return null;
	}


	

}
