package com.kapphk.web.bean.product;

import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_product_order
 * @author zoneyu 2016-12-19
*/
public class BeanAppProductOrder extends BaseModel {

    /**
     * 表字段：app_product_order.id 注释：主键id
     * @author zoneyu 2016-12-19
     */
    private Long id;
    /**
     * 表字段：app_product_order.order_no 注释：订单号
     * @author zoneyu 2016-12-19
     */
    private String orderNo;
    /**
     * 表字段：app_product_order.product_id 注释：商品id
     * @author zoneyu 2016-12-19
     */
    private Long productId;
    /**
     * 表字段：app_product_order.color 注释：颜色
     * @author zoneyu 2016-12-19
     */
    private String color;
    /**
     * 表字段：app_product_order.color_name 注释：颜色名称
     * @author zoneyu 2016-12-19
     */
    private String colorName;
    /**
     * 表字段：app_product_order.size 注释：尺寸
     * @author zoneyu 2016-12-19
     */
    private String size;
    /**
     * 表字段：app_product_order.size_name 注释：尺寸名称
     * @author zoneyu 2016-12-19
     */
    private String sizeName;
    /**
     * 表字段：app_product_order.price 注释：单价
     * @author zoneyu 2016-12-19
     */
    private BigDecimal price;
    /**
     * 表字段：app_product_order.qty 注释：数量
     * @author zoneyu 2016-12-19
     */
    private Integer qty;
    /**
     * 表字段：app_product_order.discount_blue 注释：蓝币抵扣
     * @author zoneyu 2016-12-19
     */
    private BigDecimal discountBlue;
    /**
     * 表字段：app_product_order.discount_red 注释：红币抵扣
     * @author zoneyu 2016-12-19
     */
    private BigDecimal discountRed;
    /**
     * 表字段：app_product_order.pay_amount 注释：支付金额
     * @author zoneyu 2016-12-19
     */
    private BigDecimal payAmount;
    /**
     * 表字段：app_product_order.pay_method 注释：支付方式
     * @author zoneyu 2016-12-19
     */
    private String payMethod;
    /**
     * 表字段：app_product_order.pay_method_type 注释：标签类型: 支付方式
     * @author zoneyu 2016-12-19
     */
    private String payMethodType;
    /**
     * 表字段：app_product_order.pay_time 注释：支付时间
     * @author zoneyu 2016-12-19
     */
    private Date payTime;
    /**
     * 表字段：app_product_order.uid 注释：用户id
     * @author zoneyu 2016-12-19
     */
    private Long uid;
    /**
     * 表字段：app_product_order.delivery_name 注释：收货名称
     * @author zoneyu 2016-12-19
     */
    private String deliveryName;
    /**
     * 表字段：app_product_order.delivery_address 注释：收货地址
     * @author zoneyu 2016-12-19
     */
    private String deliveryAddress;
    /**
     * 表字段：app_product_order.delivery_phone 注释：收货手机号
     * @author zoneyu 2016-12-19
     */
    private String deliveryPhone;
    /**
     * 表字段：app_product_order.delivery_amount 注释：运费
     * @author zoneyu 2016-12-19
     */
    private BigDecimal deliveryAmount;
    /**
     * 表字段：app_product_order.delivery_company 注释：物流公司
     * @author zoneyu 2016-12-19
     */
    private String deliveryCompany;
    /**
     * 表字段：app_product_order.delivery_no 注释：物流单号
     * @author zoneyu 2016-12-19
     */
    private String deliveryNo;
    /**
     * 表字段：app_product_order.state 注释：状态: 1:待发货 2:待收货 3:已完成 0:待支付
     * @author zoneyu 2016-12-19
     */
    private Integer state;
    /**
     * 表字段：app_product_order.comment_star 注释：评价星级
     * @author zoneyu 2016-12-19
     */
    private BigDecimal commentStar;
    /**
     * 表字段：app_product_order.comment_remark 注释：评价内容
     * @author zoneyu 2016-12-19
     */
    private String commentRemark;
    /**
     * 表字段：app_product_order.comment_time 注释：评价时间
     * @author zoneyu 2016-12-19
     */
    private Date commentTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getDiscountBlue() {
        return discountBlue;
    }

    public void setDiscountBlue(BigDecimal discountBlue) {
        this.discountBlue = discountBlue;
    }

    public BigDecimal getDiscountRed() {
        return discountRed;
    }

    public void setDiscountRed(BigDecimal discountRed) {
        this.discountRed = discountRed;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethodType() {
        return payMethodType;
    }

    public void setPayMethodType(String payMethodType) {
        this.payMethodType = payMethodType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getCommentStar() {
        return commentStar;
    }

    public void setCommentStar(BigDecimal commentStar) {
        this.commentStar = commentStar;
    }

    public String getCommentRemark() {
        return commentRemark;
    }

    public void setCommentRemark(String commentRemark) {
        this.commentRemark = commentRemark;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
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