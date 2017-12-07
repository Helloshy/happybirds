package com.kapphk.system.mapper;

import java.util.List;

import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.system.bean.PhoneCode;

/**
 * 的数据操作接口
 * @author zoneyu 2016-12-05
*/
public interface PhoneCodeMapper extends  BaseMapper<PhoneCode, Long> {
	
	/**
	 * 根据手机号查询记录
	 * @param phone
	 * @return
	 */
	public List<PhoneCode> findByPhone(String phone);
	
	/**
	 * 根据code查询记录
	 * @param code
	 * @return
	 */
	public List<PhoneCode> findByCode(String code);
}