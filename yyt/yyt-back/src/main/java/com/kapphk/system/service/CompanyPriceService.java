package com.kapphk.system.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.yyt.bean.CompanyPrice;

/**
 * 气价管理服务接口
 * @author Administrator
 *
 */
public interface CompanyPriceService extends BaseService<CompanyPrice,Long>{

	/**
	 * 分页获取气价信息
	 * @param companyPrice
	 * @param page
	 * @param rows
	 * @param sort
	 * @return
	 */
	PageInfo<Map<String, Object>> findPageByParam(CompanyPrice companyPrice, Integer page, Integer rows, String sort);
}
