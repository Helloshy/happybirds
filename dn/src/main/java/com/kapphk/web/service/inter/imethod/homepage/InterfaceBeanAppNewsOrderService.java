package com.kapphk.web.service.inter.imethod.homepage;

import com.kapphk.web.bean.homepage.BeanAppNewsOrder;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanAppNewsOrderService extends BaseService<BeanAppNewsOrder, Long> {

	/**
	 * 保存赞赏订单
	 * @author zoneyu 16-10-19
	 */
	public Result saveOrder(BeanAppNewsOrder bean,String root, Result rs) throws Exception;

}
