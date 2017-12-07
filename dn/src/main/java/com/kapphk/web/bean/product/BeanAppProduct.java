package com.kapphk.web.bean.product;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_product
 * @author zoneyu 2016-12-19
*/
public class BeanAppProduct extends BaseModel {

    /**
     * 表字段：app_product.id 注释：主键id
     * @author zoneyu 2016-12-19
     */
    private Long id;
    /**
     * 表字段：app_product.logo_path 注释：图片路径
     * @author zoneyu 2016-12-19
     */
    private String logoPath;
    /**
     * 表字段：app_product.category_id 注释：商品分类id
     * @author zoneyu 2016-12-19
     */
    private Long categoryId;
    /**
     * 表字段：app_product.item_name 注释：商品名称
     * @author zoneyu 2016-12-19
     */
    private String itemName;
    /**
     * 表字段：app_product.item_remark 注释：商品描述
     * @author zoneyu 2016-12-19
     */
    private String itemRemark;
    /**
     * 表字段：app_product.row_seq 注释：优先级
     * @author zoneyu 2016-12-19
     */
    private Integer rowSeq;
    /**
     * 表字段：app_product.price 注释：价格
     * @author zoneyu 2016-12-19
     */
    private BigDecimal price;
    /**
     * 表字段：app_product.item_comment 注释：商品详情
     * @author zoneyu 2016-12-19
     */
    private String itemComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public Integer getRowSeq() {
        return rowSeq;
    }

    public void setRowSeq(Integer rowSeq) {
        this.rowSeq = rowSeq;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemComment() {
        return itemComment;
    }

    public void setItemComment(String itemComment) {
        this.itemComment = itemComment;
    }
}