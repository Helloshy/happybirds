package com.kapphk.web.bean.user;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_area
 * @author zoneyu 2016-09-22
*/
public class BeanUserArea extends BaseModel {

    /**
     * 表字段：user_area.id 注释：主键id
     * @author zoneyu 2016-09-22
     */
    private Long id;
    /**
     * 表字段：user_area.uid 注释：用户id
     * @author zoneyu 2016-09-22
     */
    private Long uid;
    /**
     * 表字段：user_area.province 注释：省
     * @author zoneyu 2016-09-22
     */
    private String province;
    /**
     * 表字段：user_area.record_type 注释：类型 1:直接管辖 2:间接管辖
     * @author zoneyu 2016-09-22
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
}