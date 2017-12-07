package com.kapphk.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.service.CompanyHallService;
import com.kapphk.yyt.bean.CompanyHall;
import com.kapphk.yyt.mapper.CompanyHallMapper;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class CompanyHallServiceImpl extends BaseServiceImpl<CompanyHall,Long> implements CompanyHallService {

    @Autowired
    private CompanyHallMapper mapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

	@Override
	public List<CompanyHall> findListByCompanyId(Long companyId) {
		CompanyHall companyhall = new CompanyHall();
		companyhall.setCompanyId(companyId);
		return mapper.findList(companyhall);
	}

	@Override
	public PageInfo<Map<String,Object>> findPageByParam(CompanyHall companyHall, Integer page, Integer rows,
			String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.findListByParam(companyHall);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(resultMaps);
        return pageInfo;
	}

	@Override
	public List<CompanyHall> findListByUsId(Long id) {
		
		return mapper.finListByUid(id);
	}

	

}
