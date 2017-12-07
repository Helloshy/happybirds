package com.kapphk.web.controller.inter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.user.BeanUserAddress;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserAddressService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 用户收货地址控制层
 * @author zoneyu 16-12-3
 */
@RestController
@RequestMapping("/userAddress/")
public class InterfaceBeanUserAddressController extends BaseController<BeanUserAddress, Long> {
	
	@Autowired
	private InterfaceBeanUserAddressService service ;
	
	/**
	 * 收货地址列表
	 * @author zoneyu 16-12-3
	 */
	@RequestMapping("getAddressList.do")
	public Result getAddressList(BeanUserAddress bean){
		Result rs = new Result();
		try {
			rs = service.getAddressList(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 新增收货地址列表
	 * @author zoneyu 16-12-3
	 */
	@RequestMapping("saveAddress.do")
	public Result saveAddress(BeanUserAddress bean){
		Result rs = new Result();
		try {
			rs = service.saveAddress(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 删除收货地址列表
	 * @author zoneyu 16-12-3
	 */
	@RequestMapping("deleteAddress.do")
	public Result deleteAddress(BeanUserAddress bean){
		Result rs = new Result();
		try {
			rs = service.deleteAddress(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
