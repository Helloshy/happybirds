package com.kapphk.web.bean.user;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_report_borrow
 * @author zoneyu 2016-11-16
*/
public class BeanReportBorrow extends BaseModel {

    /**
     * 表字段：app_report_borrow.id 注释：主键id
     * @author zoneyu 2016-11-16
     */
    private Long id;
    /**
     * 表字段：app_report_borrow.phone 注释：手机号
     * @author zoneyu 2016-11-16
     */
    private String phone;
    /**
     * 表字段：app_report_borrow.actual_name 注释：真实姓名
     * @author zoneyu 2016-11-16
     */
    private String actualName;
    /**
     * 表字段：app_report_borrow.borrow_amount 注释：借款金额
     * @author zoneyu 2016-11-16
     */
    private BigDecimal borrowAmount;
    /**
     * 表字段：app_report_borrow.use_remark 注释：用途
     * @author zoneyu 2016-11-16
     */
    private String useRemark;
    /**
     * 表字段：app_report_borrow.start_date 注释：借款日
     * @author zoneyu 2016-11-16
     */
    private String startDate;
    /**
     * 表字段：app_report_borrow.end_date 注释：借款到期日
     * @author zoneyu 2016-11-16
     */
    private String endDate;
    /**
     * 表字段：app_report_borrow.borrow_year 注释：计划借款期限
     * @author zoneyu 2016-11-16
     */
    private String borrowYear;
    /**
     * 表字段：app_report_borrow.year_rate 注释：年利率
     * @author zoneyu 2016-11-16
     */
    private BigDecimal yearRate;
    /**
     * 表字段：app_report_borrow.year_interest 注释：年利息
     * @author zoneyu 2016-11-16
     */
    private BigDecimal yearInterest;
    /**
     * 表字段：app_report_borrow.return_remark 注释：还款及支付利息情况
     * @author zoneyu 2016-11-16
     */
    private String returnRemark;
    /**
     * 表字段：app_report_borrow.balance_amount 注释：剩余借款金额
     * @author zoneyu 2016-11-16
     */
    private BigDecimal balanceAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public String getUseRemark() {
        return useRemark;
    }

    public void setUseRemark(String useRemark) {
        this.useRemark = useRemark;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBorrowYear() {
        return borrowYear;
    }

    public void setBorrowYear(String borrowYear) {
        this.borrowYear = borrowYear;
    }

    public BigDecimal getYearRate() {
        return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    public BigDecimal getYearInterest() {
        return yearInterest;
    }

    public void setYearInterest(BigDecimal yearInterest) {
        this.yearInterest = yearInterest;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}