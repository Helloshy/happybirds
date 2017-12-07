package com.kapphk.web.service.web.imethod.system;

import java.util.List;

import com.kapphk.web.bean.system.BeanLog;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

public interface BeanLogService {

	/**
	 * 查询
	 * @author zoneyu 16-6-12
	 */
	public Result getList(BeanLog bean, GridReq gridReq, Result rs) throws Exception ;

	/**
	 * 删除
	 * @author dengwen 
	 * 2016-9-20下午2:43:58
	 */
	public Result delete(List<Long> asList, Result rs)throws Exception ;

}


  