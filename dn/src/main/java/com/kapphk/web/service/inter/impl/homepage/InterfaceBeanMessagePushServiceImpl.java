package com.kapphk.web.service.inter.impl.homepage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanMessagePush;
import com.kapphk.web.dao.mapper.system.BeanMessagePushMapper;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanMessagePushService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 系统消息业务层
 * @author dengwen 
 * 2016-11-12下午3:33:12
 */
@Service
public class InterfaceBeanMessagePushServiceImpl implements
		InterfaceBeanMessagePushService {

	@Autowired
	private BeanMessagePushMapper mapper;
	
	/**
	 * 查询列表
	 */
	@Override
	public Result getMessagePushList(Long uid, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(uid)){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			List<Map<String,Object>> list = mapper.getMessagePushList(uid,(page-1)*GridReq.ROWS,GridReq.ROWS);
			rs.put("info", list) ;
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public BeanMessagePush getMessagePushDetail(Long id) {
		return mapper.findById(id);
	}

	/**
	 * 消息红点
	 * @author zoneyu 16-12-3
	 */
	public Result getAmount(String time, Result rs) throws Exception {
		if(ValidateUtils.isBlank(time)){
			time = DateUtils.getLocalDate() ;
		}
		Date date = DateUtils.parseDate("yyyy-MM-dd HH:mm:ss", time) ;
		int count = mapper.getAmount(date) ;
		if(count > 0){
			rs.put("info", 1) ;
		}else{
			rs.put("info", 0) ;
		}
		return rs ;
	}

}
