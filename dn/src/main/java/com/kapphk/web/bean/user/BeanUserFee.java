package com.kapphk.web.bean.user;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_fee
 * @author zoneyu 2016-09-22
*/
public class BeanUserFee extends BaseModel {

    /**
     * 表字段：user_fee.id 注释：主键id
     * @author zoneyu 2016-09-22
     */
    private Long id;
    /**
     * 表字段：user_fee.uid 注释：用户id
     * @author zoneyu 2016-09-22
     */
    private Long uid;
    /**
     * 表字段：user_fee.amount 注释：缴纳费用
     * @author zoneyu 2016-09-22
     */
    private BigDecimal amount;
    /**
     * 表字段：user_fee.remark 注释：备注(填写当次升级的身份)
     * @author zoneyu 2016-09-22
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}