package com.kapphk.web.bean.product;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_product_category
 * @author zoneyu 2016-12-19
*/
public class BeanAppProductCategory extends BaseModel {

    /**
     * 表字段：app_product_category.id 注释：主键id
     * @author zoneyu 2016-12-19
     */
    private Long id;
    /**
     * 表字段：app_product_category.item_name 注释：商品分类
     * @author zoneyu 2016-12-19
     */
    private String itemName;
    /**
     * 表字段：app_product_category.row_seq 注释：优先级
     * @author zoneyu 2016-12-19
     */
    private Integer rowSeq;

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

    public Integer getRowSeq() {
        return rowSeq;
    }

    public void setRowSeq(Integer rowSeq) {
        this.rowSeq = rowSeq;
    }
}