package com.kapphk.system.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.yyt.bean.CompanyHall;

/**
 * 公司信息数据服务接口
 * @author Administrator
 *
 */
public interface CompanyHallService extends BaseService<CompanyHall,Long>{

	/**
	 * 根据公司id获取营业厅
	 * @param companyId
	 * @return
	 */
	List<CompanyHall> findListByCompanyId(Long companyId);

	/**
	 * 获取营业厅列表
	 * @param companyHall
	 * @param page
	 * @param rows
	 * @param sort
	 * @return
	 */
	PageInfo<Map<String,Object>> findPageByParam(CompanyHall companyHall, Integer page, Integer rows, String sort);

	/**
	 * 根据用户id获取营业厅
	 * @param id
	 * @return
	 */
	List<CompanyHall> findListByUsId(Long id);

}
