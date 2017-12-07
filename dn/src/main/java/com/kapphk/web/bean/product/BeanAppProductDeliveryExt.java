package com.kapphk.web.bean.product;

import com.kapphk.web.bean.BaseModel;

import java.math.BigDecimal;

/**
 * 主键：app_product_delivery
 * @author zoneyu 2016-12-19
*/
public class BeanAppProductDeliveryExt extends BaseModel {

    private Byte [] id;
    /**
     * 表字段：app_product_delivery.province_id 注释：省id
     * @author zoneyu 2016-12-19
     */
    private String provinces;
    /**
     * 表字段：app_product_delivery.city_id 注释：城市id
     * @author zoneyu 2016-12-19
     */
    private String citys;
    /**
     * 表字段：app_product_delivery.price 注释：单价
     * @author zoneyu 2016-12-19
     */
    private BigDecimal price;

    public void setId(Byte[] id) {
        this.id = id;
    }

    public Byte[] getId() {
        return id;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}