package com.kapphk.web.service.web.imethod.shop;

import com.kapphk.web.bean.product.BeanAppProductCategory;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

import java.util.List;
import java.util.Map;

/** 商品分类业务处理类
 * Created by shy on 2016/12/19.
 */


public interface BeanProductCategoryService {

    /**
     * 查询列表
     * @param rs
     * @param bean
     * @param gridReq
     * @return
     */
    Result searchList(Result rs, BeanAppProductCategory bean, GridReq gridReq)throws Exception;

    /**
     * 保存/修改
     * @param rs
     * @param bean
     * @return
     */
    Result saveData(Result rs, BeanAppProductCategory bean) throws Exception;

    /**
     * 详情
     * @param rs
     * @param bean
     * @return
     */
    Result getData(Result rs, BeanAppProductCategory bean) throws Exception;

    /**
     * 批量删除
     * @param rs
     * @param longs
     * @return
     */
    Result delete(Result rs, List<Long> longs) throws Exception;

    /**
     * 查询所有分类
     * @return
     */
    List<Map<String, Object>> findAll();
}
