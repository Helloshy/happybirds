package com.kapphk.web.service.web.imethod.shop;

import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by shy on 2016/12/21.
 */


public interface BeanProductOrderService {

    /**
     * 条件查询列表
     * @param rs
     * @param param
     * @param gridReq
     * @return
     */
    Result searchList(Result rs, Map<String, Object> param, GridReq gridReq) throws Exception;

    /**
     *  更新
     * @param bean
     * @param rs
     * @return
     */
    Result update(BeanAppProductOrder bean, Result rs) throws Exception;

    /**
     * 详情
     * @param rs
     * @param bean
     * @return
     */
    Result getData(Result rs, BeanAppProductOrder bean) throws Exception;

    void exportExcel(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response);

    /**
     * 商城销售报表列表
     * @param rs
     * @param param
     * @param gridReq
     * @return
     */
    Result searchListForSellChart(Result rs, Map<String, Object> param, GridReq gridReq) throws Exception;

    /**
     * 导出商城销售报表
     * @param param
     * @param request
     * @param response
     */
    void exportExcelSellChart(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response);

    /**
     * 商城产品销售报表列表
     * @param rs
     * @param param
     * @param gridReq
     * @return
     */
    Result searchListForProductSellChart(Result rs, Map<String, Object> param, GridReq gridReq);
    /**
     * 导出商城产品销售报表
     * @param param
     * @param request
     * @param response
     */
    void exportExcelProductSellChart(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response);

    /**
     * 商品库存报表列表
     * @param rs
     * @param param
     * @param gridReq
     * @return
     */
    Result searchListForProductInventoryChart(Result rs, Map<String, Object> param, GridReq gridReq);

    /**
     * 导出商品库存报表列表
     * @param param
     * @param request
     * @param response
     */
    void exportExcelProductInventoryChart(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response);
}
