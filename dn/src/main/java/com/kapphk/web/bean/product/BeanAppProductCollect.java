package com.kapphk.web.bean.product;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_product_collect
 * @author zoneyu 2016-12-19
*/
public class BeanAppProductCollect extends BaseModel {

    /**
     * 表字段：app_product_collect.id 注释：主键id
     * @author zoneyu 2016-12-19
     */
    private Long id;
    /**
     * 表字段：app_product_collect.uid 注释：用户id
     * @author zoneyu 2016-12-19
     */
    private Long uid;
    /**
     * 表字段：app_product_collect.product_id 注释：商品id
     * @author zoneyu 2016-12-19
     */
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}