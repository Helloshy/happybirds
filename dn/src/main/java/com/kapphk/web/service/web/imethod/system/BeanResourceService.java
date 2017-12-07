package com.kapphk.web.service.web.imethod.system;

import java.util.List;
import java.util.Map;

import com.kapphk.web.bean.system.BeanStandardResource;

public interface BeanResourceService {

	/**
	 * 查询菜单目录
	 * @author dengwen 
	 * 2016-9-16下午1:39:00
	 */
	public List<BeanStandardResource> findUserResourcesByUserId(Long userId,List<BeanStandardResource> list);

	/**
	 * 初始化菜单下拉框
	 * @author dengwen 
	 * 2016-9-18下午2:45:18
	 */
	public List<Map<String, Object>> searchResourceList(List<Map<String, Object>> list);

}
