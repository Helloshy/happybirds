package com.kapphk.web.bean.teacher;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 级别：app_teacher_level
 * @author zoneyu 2016-10-08
*/
public class BeanTeacherLevel extends BaseModel {

    /**
     * 表字段：app_teacher_level.id 注释：级别
     * @author zoneyu 2016-10-08
     */
    private String id;
    /**
     * 表字段：app_teacher_level.rate 注释：提成比例
     * @author zoneyu 2016-10-08
     */
    private BigDecimal rate;
    /**
     * 表字段：app_teacher_level.record_type 注释：类型 1:动能名师 2:陪伴师
     * @author zoneyu 2016-10-08
     */
    private Integer recordType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
}