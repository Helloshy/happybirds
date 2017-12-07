package com.kapphk.yyt.mapper;

import java.util.List;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.yyt.bean.AppCompanyService;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-06
*/
public interface CompanyServiceMapper extends BaseMapper<AppCompanyService, Long> {

	List<AppCompanyService> findCompanyServiceList();
	
	List<AppCompanyService> findHelpList();
}