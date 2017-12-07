package com.kapphk.system.service;

import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.SysLog;
import com.kapphk.web.utils.Result;

public interface SysLogService extends BaseService<SysLog, Long> {

	/**
	 * 查询
	 * @author EXIA
	 */
	public Result getList(SysLog bean, String userName, int page, int rows, Result rs) throws Exception ;
	
	public Result saveData(SysLog bean, Result rs) throws Exception ;
	
	public Result delete(String ids, Result rs) throws Exception ;
}


  