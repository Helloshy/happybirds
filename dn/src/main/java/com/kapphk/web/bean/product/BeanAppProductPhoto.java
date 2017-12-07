package com.kapphk.web.bean.product;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_product_photo
 * @author zoneyu 2016-12-19
*/
public class BeanAppProductPhoto extends BaseModel {

    /**
     * 表字段：app_product_photo.id 注释：主键id
     * @author zoneyu 2016-12-19
     */
    private Long id;
    /**
     * 表字段：app_product_photo.product_id 注释：商品分类id
     * @author zoneyu 2016-12-19
     */
    private Long productId;
    /**
     * 表字段：app_product_photo.logo_path 注释：图片路径
     * @author zoneyu 2016-12-19
     */
    private String logoPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}