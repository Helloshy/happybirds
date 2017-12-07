package com.kapphk.yyt.service;

import com.kapphk.base.service.BaseService;
import com.kapphk.system.bean.PhoneCode;
import com.kapphk.web.utils.Result;

public interface PhoneCodeService extends BaseService<PhoneCode, Long>{
	

	/**
	 * 保存验证码
	 * @param photoCode
	 * @param result
	 * @return
	 */
	public Result save(PhoneCode photoCode,Result result);
	
	/**
	 * 验证短信验证码
	 * @param msmCode
	 * @param result
	 * @return
	 */
	public Result checkSmsCode(String phone,String smsCode,Result result);
	
	
	
}
