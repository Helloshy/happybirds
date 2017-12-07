package com.kapphk.web.service.inter.imethod.homepage;

import com.kapphk.web.bean.homepage.BeanGuide;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanGuideService extends BaseService<BeanGuide, Long> {

	/**
	 * 首页轮播图数据
	 * @author zoneyu 16-10-10
	 */
	public Result getHomeGuideList(Result rs) throws Exception ;

	/**
	 * 轮播图详情
	 * @author zoneyu 16-10-10
	 */
	public String getGuideDetail(Long id) throws Exception ;

	/**
	 * 动能名师轮播图数据
	 * @author dengwen 
	 * 2016-10-17下午3:21:40
	 */
	public Result getTeacherGuideList(BeanGuide bean, Result rs) throws Exception;

	/**
	 * 动能名师区域与级别
	 * @author dengwen 
	 * 2016-10-17下午3:39:10
	 */
	public Result getDistrictList(Result rs) throws Exception;
}
