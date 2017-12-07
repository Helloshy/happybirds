package com.kapphk.web.bean.course;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_goods
 * @author zoneyu 2016-10-20
*/
public class BeanAppGoods extends BaseModel {

    /**
     * 表字段：app_goods.id 注释：主键id
     * @author zoneyu 2016-10-20
     */
    private Long id;
    /**
     * 表字段：app_goods.item_name 注释：物品名称
     * @author zoneyu 2016-10-20
     */
    private String itemName;
    /**
     * 表字段：app_goods.currency 注释：欧币
     * @author zoneyu 2016-10-20
     */
    private BigDecimal currency;
    /**
     * 表字段：app_goods.logo_path 注释：图片路径
     * @author zoneyu 2016-10-20
     */
    private String logoPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCurrency() {
        return currency;
    }

    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}