package com.kapphk.web.bean.user;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_cash_apply
 * @author zoneyu 2016-10-28
*/
public class BeanUserCashApply extends BaseModel {

    /**
     * 表字段：user_cash_apply.id 注释：主键id
     * @author zoneyu 2016-10-28
     */
    private Long id;
    /**
     * 表字段：user_cash_apply.uid 注释：用户id
     * @author zoneyu 2016-10-28
     */
    private Long uid;
    /**
     * 表字段：user_cash_apply.bank_name 注释：开户银行
     * @author zoneyu 2016-10-28
     */
    private String bankName;
    /**
     * 表字段：user_cash_apply.bank_info 注释：支行信息
     * @author zoneyu 2016-10-28
     */
    private String bankInfo;
    /**
     * 表字段：user_cash_apply.card_id 注释：银行卡信息
     * @author zoneyu 2016-10-28
     */
    private String cardId;
    /**
     * 表字段：user_cash_apply.card_signature 注释：开户人姓名
     * @author zoneyu 2016-10-28
     */
    private String cardSignature;
    /**
     * 表字段：user_cash_apply.amount 注释：提现金额
     * @author zoneyu 2016-10-28
     */
    private BigDecimal amount;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardSignature() {
        return cardSignature;
    }

    public void setCardSignature(String cardSignature) {
        this.cardSignature = cardSignature;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}