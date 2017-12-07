package com.kapphk.web.dao.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.product.BeanAppProduct;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-19
*/
public interface BeanAppProductMapper extends BaseMapper<BeanAppProduct, Long> {

	/**
	 * 商品列表
	 * @author dengwen 
	 * 2016-12-19下午5:09:11
	 */
	public List<Map<String,Object>> getProductList(@Param("categoryId")Long categoryId,@Param("itemName")String itemName,
			@Param("sales")Integer sales,@Param("price")Integer price,
			 @Param("page")int page, @Param("rows")Integer rows);

	/**
	 * 商品详情
	 * @author dengwen 
	 * 2016-12-19下午5:09:03
	 */
	public Map<String, Object> getProductDetail(@Param("id")Long id,@Param("uid")Long uid);

    /**
     * 查询列表
     * @param page
     * @param rows
     * @return
     */
    List<Map<String, Object>> findList(@Param("page")int page, @Param("rows")int rows);

	/**
	 * 条件查询列表
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> findListByParam(@Param("param") Map<String, Object> param,
											 @Param("page") Integer page,@Param("rows") Integer rows);

	/**
	 * 条件查询总记录数
	 * @param param
	 * @return
	 */
	Integer findListByParamCount(@Param("param") Map<String, Object> param);

	/**
	 * 插入并返回id值
	 * @param beanAppProduct
	 * @return
	 */
	Long insertOverride(BeanAppProduct beanAppProduct);


}