package com.kapphk.yyt.bean;

import java.math.BigDecimal;

import com.kapphk.base.bean.BaseModel;

/**
 * 记录ID：user_order
 * @author zoneyu 2016-12-06
*/
public class UserOrder extends BaseModel {

    /**
     * 表字段：user_order.id 注释：记录ID
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：user_order.company_id 注释：公司id
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    /**
     * 表字段：user_order.unid 注释：用户燃气编号
     * @author zoneyu 2016-12-06
     */
    private Long unid;
    
    private String orderNo;
    
    private Integer isPay;
    /**
     * 表字段：user_order.amount 注释：费用
     * @author zoneyu 2016-12-06
     */
    private BigDecimal amount;
    /**
     * 表字段：user_order.balance_amount 注释：当前余额
     * @author zoneyu 2016-12-06
     */
    private BigDecimal balanceAmount;
    /**
     * 表字段：user_order.sid 注释：操作员id
     * @author zoneyu 2016-12-06
     */
    private Long sid;
    /**
     * 表字段：user_order.uid 注释：用户id
     * @author zoneyu 2016-12-06
     */
    private Long uid;
    /**
     * 表字段：user_order.pay_method 注释：支付方式 1:现金支付 2:微信支付
     * @author zoneyu 2016-12-06
     */
    private Integer payMethod;
    /**
     * 表字段：user_order.remark 注释：付款备注
     * @author zoneyu 2016-12-06
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUnid() {
        return unid;
    }

    public void setUnid(Long unid) {
        this.unid = unid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
    
    
}