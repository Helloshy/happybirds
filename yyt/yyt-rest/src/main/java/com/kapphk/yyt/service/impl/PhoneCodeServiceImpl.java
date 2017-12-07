package com.kapphk.yyt.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.system.bean.PhoneCode;
import com.kapphk.system.bean.User;
import com.kapphk.system.mapper.PhoneCodeMapper;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.SmsClientSend;
import com.kapphk.yyt.service.PhoneCodeService;

@Service
public class PhoneCodeServiceImpl extends BaseServiceImpl<PhoneCode, Long> implements PhoneCodeService{

	@Autowired
	private PhoneCodeMapper mapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	@Override
	public Result save(PhoneCode phoneCode, Result result) {
		//判断手机号是否为空
		if(StringUtils.isBlank(phoneCode.getPhone())){
			result.setError(MSG.CUS_ERROR);
			result.setMsg("手机号不能为空！");
			return result;
		}
		//生成验证码
		String randomCode = Common.createRandom(true, 4);
		phoneCode.setCode(randomCode);
		List<PhoneCode> list = mapper.findByPhone(phoneCode.getPhone());
		if(list != null && list.size() > 0){
			phoneCode.setId(list.get(0).getId());
			phoneCode.setCreateTime(new Date());
			mapper.update(phoneCode);
		}else{
			phoneCode.setCreateTime(new Date());
			mapper.insert(phoneCode);
		}
		
		String status = SmsClientSend.sendSms(phoneCode.getPhone(), "【网上营业厅】您的的验证码是 " + randomCode);
		if (status.equalsIgnoreCase("Success")){
			result.setMsg("短信发送成功！");
		}else{
 		    result.setMsg("短信发送失败！");
 		    throw new RuntimeException();
		}
		return result;
	}

	@Override
	public Result checkSmsCode(String phone,String smsCode,Result result) {
		if(StringUtils.isBlank(phone)){
			result.setError(MSG.CUS_ERROR);
			result.setMsg("手机号不能为空！");
			return result;
		}
		if(StringUtils.isBlank(smsCode)){
			result.setError(MSG.CUS_ERROR);
			result.setMsg("手机验证码不能为空！");
			return result;
		}
		PhoneCode phoneCode = new PhoneCode();
		phoneCode.setPhone(phone);
		phoneCode.setCode(smsCode);
		List<PhoneCode> list = mapper.findAll(phoneCode);
		if(list != null && list.size() > 0){
			return result;
		}
		result.setError(MSG.CUS_ERROR);
		result.setMsg("验证码错误！");
		return result;
	}
	
}
