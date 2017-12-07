package com.kapphk.web.service.inter.imethod.user;

import com.kapphk.web.bean.system.BeanOpinion;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserOpinionService {

	/**
	 * 保存意见
	 * @author dengwen 
	 * 2016-10-17上午10:25:49
	 */
	public Result saveOpinion(BeanOpinion bean, Result rs)throws Exception;

	/**
	 * 获取留言列表
	 * @author dengwen 
	 * 2016-10-17上午10:46:18
	 */
	public Result getOpinionList(BeanOpinion bean, Result rs)throws Exception;

}
