package com.kapphk.web.dao.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.dao.mapper.BaseMapper;

/**
 * 主键的数据操作接口
 * @author zoneyu 2016-12-19
*/
public interface BeanAppProductOrderMapper extends BaseMapper<BeanAppProductOrder, Long> {

    /**
     * 获取商品评价列表
     *
     * @author dengwen
     * 2016-12-21上午10:54:48
     */
    public List<Map<String, Object>> getProductCommtentList(@Param("productId") Long productId, @Param("page") int page, @Param("rows") Integer rows);

    /**
     * 获取订单详情
     *
     * @author dengwen
     * 2016-12-21上午10:54:59
     */
    public Map<String, Object> findDetailById(@Param("id") Long id);

    /**
     * 获取订单列表
     *
     * @author dengwen
     * 2016-12-21下午1:39:16
     */
    public List<Map<String, Object>> getProductOrderList(@Param("param") BeanAppProductOrder param, @Param("page") int page, @Param("rows") Integer rows);

    /**
     * 条件查询列表
     *
     * @param param
     * @param page
     * @param rows
     * @return
     */
    List<Map<String, Object>> findListByParam(@Param("param") Map<String, Object> param,
                                              @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * 条件查询列表总记录数
     *
     * @param param
     * @return
     */
    Integer findListByParamCount(@Param("param") Map<String, Object> param);

    Integer updateByOrderNo(BeanAppProductOrder bean);


    List<Map<String, Object>> findListByParamExcel(@Param("param") Map<String, Object> param,
                                                   @Param("page") Integer page,
                                                   @Param("rows") Integer rows);

    /**
     * 条件查询列表总记录数
     *
     * @param param
     * @return
     */
    Integer findListByParamExcelCount(@Param("param") Map<String, Object> param);

    /**
     * 商城销售报表列表
     * @param param
     * @param page
     * @param rows
     * @return
     */
    List<Map<String,Object>> findSellChartByParam(@Param("param") Map<String, Object> param, @Param("page") Integer page,@Param("rows") Integer rows);

    /**
     * 更新商品订单状态
     *
     * @author dengwen
     * 2016-12-24下午2:53:51
     */
    public void updateStateByIds(@Param("ids") List<Long> ids);

    public int updateStateByDate(@Param("date") String date);

    Integer findSellChartByParamCount(@Param("param") Map<String, Object> param);
    /**
     * 商城产品销售报表列表
     * @param param
     * @param page
     * @param rows
     * @return
     */
    List<Map<String,Object>> findProductSellChartByParam(@Param("param") Map<String, Object> param, @Param("page") Integer page,@Param("rows") Integer rows);


    Integer findProductSellChartByParamCount(@Param("param") Map<String, Object> param);

    /**
     * 商城库存报表
     * @param param
     * @param page
     * @param rows
     * @return
     */
    List<Map<String,Object>> findProductInventoryChartByParam(@Param("param") Map<String, Object> param, @Param("page") Integer page,@Param("rows") Integer rows);

    Integer findProductInventoryChartByParamCount(@Param("param")Map<String, Object> param);

}