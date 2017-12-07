package com.kapphk.web.service.inter.impl.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.user.BeanUserAddress;
import com.kapphk.web.dao.mapper.user.BeanUserAddressMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserAddressService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 收货地址业务层(接口)
 * @author zoenyu 16-12-3
 */
@Service("interfaceBeanUserAddressService")
public class InterfaceBeanUserAddressServiceImpl extends BaseServiceImpl<BeanUserAddress, Long> 
					implements InterfaceBeanUserAddressService {
	
	@Autowired
	private BeanUserAddressMapper mapper;

	public void init() {
		this.setMapper(this.mapper);
	}
	
	/**
	 * 收货地址列表
	 * @author zoneyu 16-12-3
	 */
	public Result getAddressList(BeanUserAddress bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			List<Map<String,Object>> list = mapper.findByUidList(bean.getUid()) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 新增&修改收货地址列表
	 * @author zoneyu 16-12-3
	 */
	public Result saveAddress(BeanUserAddress bean, Result rs) throws Exception {
		Integer isDefault = bean.getIsDefault() ;
		if(isDefault == 1){
			mapper.updateDefault(bean.getUid()) ;
		}
		if(ValidateUtils.isBlank(bean.getId())){//新增
			//更新其他的为非默认
			bean.setCreateTime(new Date());
			mapper.insert(bean) ;
		}else{
			mapper.update(bean) ;
		}
		return rs ;
	}

	/**
	 * 删除收货地址列表
	 * @author zoneyu 16-12-3
	 */
	public Result deleteAddress(BeanUserAddress bean, Result rs) throws Exception {
		mapper.deleteByEntity(bean) ;
		return rs ;
	}

}
