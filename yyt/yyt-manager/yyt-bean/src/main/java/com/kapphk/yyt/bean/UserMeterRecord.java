package com.kapphk.yyt.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.base.bean.BaseModel;

/**
 * 记录ID：user_meter_record
 * @author zoneyu 2016-12-06
*/
public class UserMeterRecord extends BaseModel {

    /**
     * 表字段：user_meter_record.id 注释：记录ID
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：user_meter_record.company_id 注释：公司id
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    /**
     * 表字段：user_meter_record.unid 注释：用户燃气编号
     * @author zoneyu 2016-12-06
     */
    private Long unid;
    /**
     * 表字段：user_meter_record.last_count 注释：上期表数
     * @author zoneyu 2016-12-06
     */
    private Integer lastCount;
    /**
     * 表字段：user_meter_record.current_count 注释：当期表数
     * @author zoneyu 2016-12-06
     */
    private Integer currentCount;
    /**
     * 表字段：user_meter_record.amount 注释：费用
     * @author zoneyu 2016-12-06
     */

    /*单价*/
    private BigDecimal price;

    private BigDecimal amount;
    /**
     * 表字段：user_meter_record.balance_amount 注释：当前余额
     * @author zoneyu 2016-12-06
     */
    private BigDecimal balanceAmount;
    /**
     * 表字段：user_meter_record.sid 注释：抄表员
     * @author zoneyu 2016-12-06
     */
    private Long sid;
    /**
     * 表字段：user_meter_record.uid 注释：用户id
     * @author zoneyu 2016-12-06
     */
    private Long uid;
    /**
     * 表字段：user_meter_record.record_time 注释：抄表时间
     * @author zoneyu 2016-12-06
     */
    private Date recordTime;
    /**
     * 表字段：user_meter_record.remark 注释：调整备注 保存调整的信息
     * @author zoneyu 2016-12-06
     */
    private String remark;
    /**
     * 表字段：user_meter_record.state 注释：状态 0:未审核 1:审核
     * @author zoneyu 2016-12-06
     */
    private Integer state;
    /**
     * 表字段：user_meter_record.update_by 注释：操作人
     * @author zoneyu 2016-12-06
     */
    private Long updateBy;
    /**
     * 表字段：user_meter_record.update_time 注释：更新时间
     * @author zoneyu 2016-12-06
     */
    private Date updateTime;

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

    public Integer getLastCount() {
        return lastCount;
    }

    public void setLastCount(Integer lastCount) {
        this.lastCount = lastCount;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
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

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}