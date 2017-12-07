package com.kapphk.web.service.inter.imethod.user;

import com.kapphk.web.bean.system.BeanUserApply;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserApplyService {

	/**
	 * 获取动能留学课程
	 * @author dengwen 
	 * 2016-10-17下午6:04:13
	 */
	public Result getCourseList(Integer page, Result rs)throws Exception;

	/**
	 * 保存动能留学申请
	 * @author dengwen 
	 * 2016-10-17下午6:34:53
	 */
	public Result saveApply(BeanUserApply bean, Result rs)throws Exception;

}
