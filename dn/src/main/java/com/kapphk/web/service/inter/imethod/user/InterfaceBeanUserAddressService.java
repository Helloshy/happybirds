package com.kapphk.web.service.inter.imethod.user;

import com.kapphk.web.bean.user.BeanUserAddress;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserAddressService extends BaseService<BeanUserAddress, Long> {

	/**
	 * 收货地址列表
	 * @author zoneyu 16-12-3
	 */
	public Result getAddressList(BeanUserAddress bean, Result rs) throws Exception ;

	/**
	 * 新增收货地址列表
	 * @author zoneyu 16-12-3
	 */
	public Result saveAddress(BeanUserAddress bean, Result rs) throws Exception ;

	/**
	 * 删除收货地址列表
	 * @author zoneyu 16-12-3
	 */
	public Result deleteAddress(BeanUserAddress bean, Result rs) throws Exception ;

}
