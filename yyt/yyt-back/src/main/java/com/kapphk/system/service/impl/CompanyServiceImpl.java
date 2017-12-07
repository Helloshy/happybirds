package com.kapphk.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.service.CompanyService;
import com.kapphk.yyt.bean.Company;
import com.kapphk.yyt.mapper.CompanyMapper;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company,Long> implements CompanyService {

    @Autowired
    private CompanyMapper mapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

	@Override
	public PageInfo<Map<String, Object>> queryCompanyByPage(Company company, Integer page, Integer rows, String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.findListByParam(company);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
	}


}
