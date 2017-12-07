package com.kapphk.web.dao.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.user.BeanUserAddress;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键ID数据操作接口
 * @author zoneyu 2016-12-03
*/
public interface BeanUserAddressMapper extends BaseMapper<BeanUserAddress, Long> {

	/**
	 * 更新其他为非默认
	 * @author zoenyu 16-12-3
	 */
	public void updateDefault(@Param("uid")Long uid);

	/**
	 * 获取用户收货地址列表
	 * @author dengwen 
	 * 2016-12-20下午2:32:38
	 */
	public List<Map<String, Object>> findByUidList(@Param("uid")Long uid);
	
}