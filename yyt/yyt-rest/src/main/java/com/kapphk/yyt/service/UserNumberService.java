package com.kapphk.yyt.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.service.BaseService;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.UserNumber;

public interface UserNumberService extends BaseService<UserNumber, Long>{
	
	/**
	 * 绑定燃气帐号
	 * @param uid 用户id
	 * @param unid
	 * @param result 用户燃气编号
	 * @param addrId 地址id
	 * @return
	 */
	public Result bindingUser(Long uid, Long unid, Result result);



	public PageInfo<UserNumber> queryUnit(String unitName, int page, int limit);

	/**
	 * 获取用户的编号
	 * @param unid
	 * @param uid
	 */
	public List<UserNumber> findListByUid(Long unid, Long uid);


	

	

	
}
