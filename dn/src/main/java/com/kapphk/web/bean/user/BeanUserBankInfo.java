package com.kapphk.web.bean.user;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_bank_info
 * @author zoneyu 2016-11-17
*/
public class BeanUserBankInfo extends BaseModel {

    /**
     * 表字段：user_bank_info.id 注释：主键id
     * @author zoneyu 2016-11-17
     */
    private Long id;
    /**
     * 表字段：user_bank_info.uid 注释：用户id
     * @author zoneyu 2016-11-17
     */
    private Long uid;
    /**
     * 表字段：user_bank_info.bank_name 注释：开户银行
     * @author zoneyu 2016-11-17
     */
    private String bankName;
    /**
     * 表字段：user_bank_info.bank_address 注释：支行信息
     * @author zoneyu 2016-11-17
     */
    private String bankAddress;
    /**
     * 表字段：user_bank_info.card_no 注释：银行卡账号
     * @author zoneyu 2016-11-17
     */
    private String cardNo;
    /**
     * 表字段：user_bank_info.acount_name 注释：开户人信息
     * @author zoneyu 2016-11-17
     */
    private String acountName;

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

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAcountName() {
        return acountName;
    }

    public void setAcountName(String acountName) {
        this.acountName = acountName;
    }
}