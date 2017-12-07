package com.kapphk.yyt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.system.bean.Guide;
import com.kapphk.system.mapper.GuideMapper;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.service.GuideService;

/**
 * 推送消息业务层
 * @author Exia
 */
@Service
public class GuideServiceImpl extends BaseServiceImpl<Guide, Long>
		implements GuideService {

	@Autowired
	private GuideMapper mapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}


}
