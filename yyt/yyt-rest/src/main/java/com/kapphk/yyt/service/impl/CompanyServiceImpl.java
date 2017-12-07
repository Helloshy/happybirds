package com.kapphk.yyt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.AppCompanyService;
import com.kapphk.yyt.mapper.CompanyServiceMapper;
import com.kapphk.yyt.service.CompanyService;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<AppCompanyService, Long> implements CompanyService{

	@Autowired
	private CompanyServiceMapper mapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}

	@Override
	public Result getCompanyService(Long companyId ,Integer recordType, Result result) {
		AppCompanyService app = new AppCompanyService();
		app.setRecordType(recordType);
		app.setCompanyId(companyId);
		List<AppCompanyService> apps = mapper.findList(app);
		if(apps!= null && apps.size() == 1){
			result.put(apps.get(0));
		}
		return result;
	}

	@Override
	public List<AppCompanyService> getList(AppCompanyService appcompanyService) {
		return mapper.findList(appcompanyService);
	}
	
}
