package com.kapphk.web.bean.product;

import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_product_delivery
 * @author zoneyu 2016-12-19
*/
public class BeanAppProductDelivery extends BaseModel {

    /**
     * 表字段：app_product_delivery.id 注释：主键id
     * @author zoneyu 2016-12-19
     */
    private Long id;
    /**
     * 表字段：app_product_delivery.province_id 注释：省id
     * @author zoneyu 2016-12-19
     */
    private String provinceId;
    /**
     * 表字段：app_product_delivery.city_id 注释：城市id
     * @author zoneyu 2016-12-19
     */
    private String cityId;
    /**
     * 表字段：app_product_delivery.price 注释：单价
     * @author zoneyu 2016-12-19
     */
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}