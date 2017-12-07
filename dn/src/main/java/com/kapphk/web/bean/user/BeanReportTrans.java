package com.kapphk.web.bean.user;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_report_trans
 * @author zoneyu 2016-11-16
*/
public class BeanReportTrans extends BaseModel {

    /**
     * 表字段：app_report_trans.id 注释：主键id
     * @author zoneyu 2016-11-16
     */
    private Long id;
    /**
     * 表字段：app_report_trans.ym 注释：月份
     * @author zoneyu 2016-11-16
     */
    private String ym;
    /**
     * 表字段：app_report_trans.from_phone 注释：转出手机号
     * @author zoneyu 2016-11-16
     */
    private String fromPhone;
    /**
     * 表字段：app_report_trans.from_name 注释：转出真实姓名
     * @author zoneyu 2016-11-16
     */
    private String fromName;
    /**
     * 表字段：app_report_trans.from_course 注释：转出课程名称
     * @author zoneyu 2016-11-16
     */
    private String fromCourse;
    /**
     * 表字段：app_report_trans.from_amount 注释：转出金额
     * @author zoneyu 2016-11-16
     */
    private BigDecimal fromAmount;
    /**
     * 表字段：app_report_trans.from_operator 注释：转出服务部门经办人
     * @author zoneyu 2016-11-16
     */
    private String fromOperator;
    /**
     * 表字段：app_report_trans.to_phone 注释：转入手机号
     * @author zoneyu 2016-11-16
     */
    private String toPhone;
    /**
     * 表字段：app_report_trans.to_name 注释：转入真实姓名
     * @author zoneyu 2016-11-16
     */
    private String toName;
    /**
     * 表字段：app_report_trans.to_course 注释：转入课程名称
     * @author zoneyu 2016-11-16
     */
    private String toCourse;
    /**
     * 表字段：app_report_trans.to_amount 注释：转入金额
     * @author zoneyu 2016-11-16
     */
    private BigDecimal toAmount;
    /**
     * 表字段：app_report_trans.to_operator 注释：转入服务部门经办人
     * @author zoneyu 2016-11-16
     */
    private String toOperator;
    /**
     * 表字段：app_report_trans.approve_who 注释：财务审核人
     * @author zoneyu 2016-11-16
     */
    private String approveWho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromCourse() {
        return fromCourse;
    }

    public void setFromCourse(String fromCourse) {
        this.fromCourse = fromCourse;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getFromOperator() {
        return fromOperator;
    }

    public void setFromOperator(String fromOperator) {
        this.fromOperator = fromOperator;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToCourse() {
        return toCourse;
    }

    public void setToCourse(String toCourse) {
        this.toCourse = toCourse;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    public String getToOperator() {
        return toOperator;
    }

    public void setToOperator(String toOperator) {
        this.toOperator = toOperator;
    }

    public String getApproveWho() {
        return approveWho;
    }

    public void setApproveWho(String approveWho) {
        this.approveWho = approveWho;
    }
}