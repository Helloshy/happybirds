package com.kapphk.web.service.inter.imethod.product;

import com.kapphk.web.utils.Result;

public interface InterfaceBeanAppProductService {

	public Result getProductList(Long categoryId, String itemName, Integer sales,
			Integer price, Integer page, Result rs) throws Exception;

	public Result getProductCategoryList(Result rs) throws Exception;

	public Result getProductDetail(Long id,Long uid,Result rs) throws Exception;

	public Result saveProductCollect(Long uid, Long productId, Integer type,
			Result rs) throws Exception;

	public Result getProductCollectList(Long uid, Integer page, Result rs) throws Exception;

	public Result deleteProductCollect(Long[] ids, Result rs) throws Exception;

}
