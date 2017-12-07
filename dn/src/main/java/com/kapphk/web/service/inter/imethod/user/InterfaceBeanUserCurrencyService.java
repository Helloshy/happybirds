package com.kapphk.web.service.inter.imethod.user;

import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserCurrencyService {

	/**
	 * 获取蓝币
	 * @author dengwen 
	 * 2016-10-14下午4:44:27
	 */
	public Result getUserCurrencyList(BeanUserCurrency bean, Integer page, Result rs)throws Exception;

	/**
	 * 签到
	 * @author dengwen 
	 * 2016-10-17下午1:57:22
	 */
	public Result saveCurrency(BeanUserCurrency bean, Result rs)throws Exception;

}
