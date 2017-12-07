package com.kapphk.yyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.base.controller.BaseController;
import com.kapphk.system.bean.PhoneCode;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.service.PhoneCodeService;

/**
 * 城市控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/web/code")
public class PhoneCodeController extends BaseController {
	
	@Autowired
	private PhoneCodeService phoneCodeService;
	
	/**
	 * 生成短信验证码
	 * @param request
	 * @param phoneCode
	 * @return
	 */
	@RequestMapping(value="/smsCode",method=RequestMethod.POST)
	public Result smsCode(PhoneCode phoneCode){
		Result result = new Result();
		try {
			result = phoneCodeService.save(phoneCode, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(MSG.ERROR);
		}
		return result;
	}
	/**
	 * 验证短息验证码
	 * @param msmCode
	 * @return
	 */
	@RequestMapping(value="/checkSmsCode")
	public Result checkSmsCode(String phone,String smsCode){
		Result result = new Result();
		try {
			result = phoneCodeService.checkSmsCode(phone,smsCode, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(MSG.ERROR);
		}
		return result;
	}
	
	
}
