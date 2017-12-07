package com.kapphk.system.service;

import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.Setting;
import com.kapphk.web.utils.Result;

public interface SettingService extends BaseService<Setting, String> {

	/**
	 * 查询
	 * @author zoneyu 16-6-3
	 */
	public Result getList(Setting bean, int page, int rows, Result rs) throws Exception ;

	/**
	 * 新增|修改
	 * @author zoneyu 16-6-3
	 */
	public Result saveData(Setting bean, Result rs) throws Exception ;

	/**
	 * 删除
	 * @author zoneyu 16-6-3
	 */
	public Result delete(String ids, Result rs) throws Exception ;



}


  