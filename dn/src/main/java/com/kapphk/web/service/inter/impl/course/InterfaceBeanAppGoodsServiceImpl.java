package com.kapphk.web.service.inter.impl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanAppGoods;
import com.kapphk.web.dao.mapper.course.BeanAppGoodsMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanGoodsService;
import com.kapphk.web.utils.Result;

/**
 * 物品业务层(接口)
 * @author zoneyu 16-10-20
 */
@Service("interfaceBeanGoodsOrderService")
public class InterfaceBeanAppGoodsServiceImpl extends BaseServiceImpl<BeanAppGoods, Long> implements
		InterfaceBeanGoodsService {

	@Autowired
	private BeanAppGoodsMapper mapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 物品列表
	 * @author zoneyu 16-10-20
	 */
	public Result getGoodsList(Result rs) throws Exception {
		List<BeanAppGoods> list = mapper.all() ;
		rs.put("info", list) ;
		return rs ;
	}

}
