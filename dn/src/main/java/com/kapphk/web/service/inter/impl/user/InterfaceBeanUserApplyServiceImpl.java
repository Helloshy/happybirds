package com.kapphk.web.service.inter.impl.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanUserApply;
import com.kapphk.web.dao.mapper.course.BeanCourseOfflineMapper;
import com.kapphk.web.dao.mapper.system.BeanUserApplyMapper;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserApplyService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 申请留学业务层
 * @author dengwen 
 * 2016-10-17下午6:03:11
 */
@Service
public class InterfaceBeanUserApplyServiceImpl implements
		InterfaceBeanUserApplyService {
	
	@Autowired
	private BeanCourseOfflineMapper mapper;
	
	@Autowired
	private BeanUserApplyMapper userApplyMapper ;

	/**
	 * 动能留学课程列表
	 */
	@Override
	public Result getCourseList(Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		rs.put("info", mapper.findCourseOfflineList((page-1)*7,7));
		return rs;
	}

	/**
	 * 保存动能留学申请
	 */
	@Override
	public Result saveApply(BeanUserApply bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUserName()) && !ValidateUtils.isBlank(bean.getUserTel()) 
				&& !ValidateUtils.isBlank(bean.getContent()) && !ValidateUtils.isBlank(bean.getRecordType())){
			bean.setCreateTime(new Date());
			userApplyMapper.insert(bean) ;
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

}
