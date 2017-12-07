package com.kapphk.system.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.yyt.bean.AppCompanyService;

/**
 * 图文服务接口
 * @author Administrator
 *
 */
public interface HelpService extends BaseService<AppCompanyService,Long>{

	
	PageInfo<AppCompanyService> findCompanyServiceByPage( Integer page, Integer rows,
			String sort);
	
	PageInfo<AppCompanyService> findHelpPage( Integer page, Integer rows,
			String sort);
	
}
