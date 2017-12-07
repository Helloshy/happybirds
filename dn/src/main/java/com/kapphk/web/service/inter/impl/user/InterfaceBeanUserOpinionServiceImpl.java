package com.kapphk.web.service.inter.impl.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanOpinion;
import com.kapphk.web.dao.mapper.system.BeanOpinionMapper;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserOpinionService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 意见反馈业务层
 * @author dengwen 
 * 2016-10-17上午10:24:37
 */
@Service
public class InterfaceBeanUserOpinionServiceImpl implements
		InterfaceBeanUserOpinionService {

	@Autowired
	private BeanOpinionMapper mapper;
	
	/**
	 * 保存意见
	 */
	@Override
	public Result saveOpinion(BeanOpinion bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getRecordType())
				&& !ValidateUtils.isBlank(bean.getContent())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 获取个人留言列表
	 */
	@Override
	public Result getOpinionList(BeanOpinion bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			bean.setRecordType(2);
			List<BeanOpinion> list = mapper.findAll(bean);
			rs.put("info", list);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

}
