package com.kapphk.web.bean.product;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_product_inventory
 * @author zoneyu 2016-12-20
*/
public class BeanAppProductInventory extends BaseModel {

    /**
     * 表字段：app_product_inventory.id 注释：主键id
     * @author zoneyu 2016-12-20
     */
    private Long id;
    /**
     * 表字段：app_product_inventory.product_id 注释：商品id
     * @author zoneyu 2016-12-20
     */
    private Long productId;
    /**
     * 表字段：app_product_inventory.color 注释：颜色
     * @author zoneyu 2016-12-20
     */
    private String color;
    /**
     * 表字段：app_product_inventory.color_name 注释：颜色名称
     * @author zoneyu 2016-12-20
     */
    private String colorName;
    /**
     * 表字段：app_product_inventory.size 注释：尺寸
     * @author zoneyu 2016-12-20
     */
    private String size;
    /**
     * 表字段：app_product_inventory.size_name 注释：尺寸名称
     * @author zoneyu 2016-12-20
     */
    private String sizeName;
    /**
     * 表字段：app_product_inventory.price 注释：单价
     * @author zoneyu 2016-12-20
     */
    private BigDecimal price;
    /**
     * 表字段：app_product_inventory.stock_qty 注释：库存数量
     * @author zoneyu 2016-12-20
     */
    private Integer stockQty;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
    
}