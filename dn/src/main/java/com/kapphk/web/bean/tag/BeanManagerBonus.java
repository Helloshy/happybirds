package com.kapphk.web.bean.tag;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_manager_bonus
 * @author zoneyu 2016-11-21
*/
public class BeanManagerBonus extends BaseModel {

    /**
     * 表字段：app_manager_bonus.id 注释：主键
     * @author zoneyu 2016-11-21
     */
    private Long id;
    /**
     * 表字段：app_manager_bonus.month 注释：月份
     * @author zoneyu 2016-11-21
     */
    private String month;
    /**
     * 表字段：app_manager_bonus.uid 注释：员工id
     * @author zoneyu 2016-11-21
     */
    private Long uid;
    /**
     * 表字段：app_manager_bonus.direct_amoun 注释：直接总金额
     * @author zoneyu 2016-11-21
     */
    private Long directAmoun;
    /**
     * 表字段：app_manager_bonus.indirect_amount 注释：间接总金额
     * @author zoneyu 2016-11-21
     */
    private Long indirectAmount;
    /**
     * 表字段：app_manager_bonus.direct_area 注释：直接管辖
     * @author zoneyu 2016-11-21
     */
    private String directArea;
    /**
     * 表字段：app_manager_bonus.direct_area_rate 注释：直接管辖比例
     * @author zoneyu 2016-11-21
     */
    private BigDecimal directAreaRate;
    /**
     * 表字段：app_manager_bonus.indirect_area 注释：间接管辖
     * @author zoneyu 2016-11-21
     */
    private String indirectArea;
    /**
     * 表字段：app_manager_bonus.indirect_area_rate 注释：间接管辖比例
     * @author zoneyu 2016-11-21
     */
    private BigDecimal indirectAreaRate;
    /**
     * 表字段：app_manager_bonus.record_type 注释：1:区域经理 2：区域总经理
     * @author zoneyu 2016-11-21
     */
    private Integer recordType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getDirectAmoun() {
        return directAmoun;
    }

    public void setDirectAmoun(Long directAmoun) {
        this.directAmoun = directAmoun;
    }

    public Long getIndirectAmount() {
        return indirectAmount;
    }

    public void setIndirectAmount(Long indirectAmount) {
        this.indirectAmount = indirectAmount;
    }

    public String getDirectArea() {
        return directArea;
    }

    public void setDirectArea(String directArea) {
        this.directArea = directArea;
    }

    public BigDecimal getDirectAreaRate() {
        return directAreaRate;
    }

    public void setDirectAreaRate(BigDecimal directAreaRate) {
        this.directAreaRate = directAreaRate;
    }

    public String getIndirectArea() {
        return indirectArea;
    }

    public void setIndirectArea(String indirectArea) {
        this.indirectArea = indirectArea;
    }

    public BigDecimal getIndirectAreaRate() {
        return indirectAreaRate;
    }

    public void setIndirectAreaRate(BigDecimal indirectAreaRate) {
        this.indirectAreaRate = indirectAreaRate;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
}