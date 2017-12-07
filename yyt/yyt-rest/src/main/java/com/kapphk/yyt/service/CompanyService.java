package com.kapphk.yyt.service;

import java.util.List;

import com.kapphk.base.service.BaseService;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.AppCompanyService;

public interface CompanyService extends BaseService<AppCompanyService, Long>{

	/**
	 * 根据类型获取 报装、报修、安检
	 * @param recordType
	 * @param result
	 */
	Result getCompanyService(Long companyId,Integer recordType, Result result);

	List<AppCompanyService> getList(AppCompanyService appcompanyService);
	
	
	
}
