package com.kapphk.web.bean.homepage;

import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_news_order
 * @author zoneyu 2016-10-19
*/
public class BeanAppNewsOrder extends BaseModel {

    /**
     * 表字段：app_news_order.id 注释：主键id
     * @author zoneyu 2016-10-19
     */
    private Long id;
    /**
     * 表字段：app_news_order.order_no 注释：订单号
     * @author zoneyu 2016-10-19
     */
    private String orderNo;
    /**
     * 表字段：app_news_order.news_id 注释：资讯id
     * @author zoneyu 2016-10-19
     */
    private Long newsId;
    /**
     * 表字段：app_news_order.uid 注释：用户id
     * @author zoneyu 2016-10-19
     */
    private Long uid;
    /**
     * 表字段：app_news_order.discount_blue 注释：蓝币抵扣
     * @author zoneyu 2016-10-19
     */
    private BigDecimal discountBlue;
    /**
     * 表字段：app_news_order.discount_red 注释：红币抵扣
     * @author zoneyu 2016-10-19
     */
    private BigDecimal discountRed;
    /**
     * 表字段：app_news_order.pay_amount 注释：实收交易金额
     * @author zoneyu 2016-10-19
     */
    private BigDecimal payAmount;
    /**
     * 表字段：app_news_order.pay_method 注释：支付方式
     * @author zoneyu 2016-10-19
     */
    private String payMethod;
    /**
     * 表字段：app_news_order.pay_method_type 注释：支付方式标签类型
     * @author zoneyu 2016-10-19
     */
    private String payMethodType;
    /**
     * 表字段：app_news_order.is_pay 注释：1已支付；0未支付 2:取消
     * @author zoneyu 2016-10-19
     */
    private Integer isPay;
    /**
     * 表字段：app_news_order.pay_time 注释：支付时间
     * @author zoneyu 2016-10-19
     */
    private Date payTime;

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

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}