package com.kapphk.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.service.CompanyPriceService;
import com.kapphk.yyt.bean.CompanyPrice;
import com.kapphk.yyt.mapper.CompanyPriceMapper;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class CompanyPriceServiceImpl extends BaseServiceImpl<CompanyPrice,Long> implements CompanyPriceService {

    @Autowired
    private CompanyPriceMapper mapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

	@Override
	public PageInfo<Map<String, Object>> findPageByParam(CompanyPrice companyPrice, Integer page, Integer rows,
			String sort) {
		PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        
        List<Map<String,Object>> resultMaps = mapper.findListByParam(companyPrice);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(resultMaps);
        return pageInfo;
	}

	

}
