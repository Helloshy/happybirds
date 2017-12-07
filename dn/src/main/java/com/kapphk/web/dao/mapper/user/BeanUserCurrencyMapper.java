package com.kapphk.web.dao.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-10-14
*/
public interface BeanUserCurrencyMapper extends BaseMapper<BeanUserCurrency, Long> {

	/**
	 * 蓝币明细
	 * @author dengwen 
	 * 2016-10-14下午5:00:23
	 */
	public List<Map<String, Object>> getUserCurrencyList(@Param("param")BeanUserCurrency bean, @Param("page")int page, @Param("rows")Integer rows);

	/**
	 * 
	 * @author dengwen 
	 * 2016-10-21上午10:34:41
	 */
	public int findSignIn(@Param("id")Long id, @Param("date")String date);
}