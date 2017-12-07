package com.kapphk.web.bean.user;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_currency
 * @author zoneyu 2016-10-14
*/
public class BeanUserCurrency extends BaseModel {

    /**
     * 表字段：user_currency.id 注释：主键id
     * @author zoneyu 2016-10-14
     */
    private Long id;
    /**
     * 表字段：user_currency.uid 注释：用户id
     * @author zoneyu 2016-10-14
     */
    private Long uid;
    /**
     * 表字段：user_currency.content 注释：来源
     * @author zoneyu 2016-10-14
     */
    private String content;
    /**
     * 表字段：user_currency.currency 注释：欧币
     * @author zoneyu 2016-10-14
     */
    private BigDecimal currency;
    /**
     * 表字段：user_currency.currency_type 注释：欧币类型 1:蓝币 2:红币
     * @author zoneyu 2016-10-14
     */
    private Integer currencyType;
    /**
     * 表字段：user_currency.record_type 注释：类型 1:课程签到 2:分享 3:赞赏 4:抵扣金额 5:每日签到
     * @author zoneyu 2016-10-14
     */
    private Integer recordType;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getCurrency() {
        return currency;
    }

    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
}