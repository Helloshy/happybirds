package com.kapphk.web.service.inter.imethod.course;

import com.kapphk.web.bean.course.BeanAppGoods;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanGoodsService extends BaseService<BeanAppGoods, Long> {

	/**
	 * 物品列表
	 * @author zoneyu 16-10-20
	 */
	public Result getGoodsList(Result rs) throws Exception ;

}
