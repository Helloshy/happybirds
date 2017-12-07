package com.kapphk.web.service.inter.imethod.product;

import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanAppProductOrderService {

	public Result getProductCommtentList(Long productId, Integer page, Result rs) throws Exception;

	public Result saveProductOrder(BeanAppProductOrder bean, String root, Result rs) throws Exception;

	public Result getProductOrderDetail(Long id, Result rs) throws Exception;

	public Result getProductOrderList(BeanAppProductOrder bean, Integer page, Result rs) throws Exception;

	public Result saveProductOrderComment(BeanAppProductOrder bean, Result rs) throws Exception;

}
