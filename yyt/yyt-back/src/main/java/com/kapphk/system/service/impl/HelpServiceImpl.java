package com.kapphk.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.service.HelpService;
import com.kapphk.yyt.bean.AppCompanyService;
import com.kapphk.yyt.mapper.CompanyServiceMapper;

/**
 * 图文服务
 * @author Administrator
 *
 */
@Service
public class HelpServiceImpl extends BaseServiceImpl<AppCompanyService,Long> implements HelpService{

    @Autowired
    private CompanyServiceMapper mapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

	@Override
	public PageInfo<AppCompanyService> findCompanyServiceByPage(Integer page, Integer rows,
			String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<AppCompanyService> resultMaps = mapper.findCompanyServiceList();
        PageInfo<AppCompanyService> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
	}

	@Override
	public PageInfo<AppCompanyService> findHelpPage(Integer page, Integer rows, String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<AppCompanyService> resultMaps = mapper.findHelpList();
        PageInfo<AppCompanyService> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
	}

}
