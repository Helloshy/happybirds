package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_system
 * @author zoneyu 2016-10-12
*/
public class BeanCourseSystem extends BaseModel {

    /**
     * 表字段：app_course_system.id 注释：主键id
     * @author zoneyu 2016-10-12
     */
    private Long id;
    /**
     * 表字段：app_course_system.system_id 注释：系统课程id
     * @author zoneyu 2016-10-12
     */
    private Long systemId;
    /**
     * 表字段：app_course_system.course_id 注释：线下/网络课程id
     * @author zoneyu 2016-10-12
     */
    private Long courseId;
    /**
     * 表字段：app_course_system.record_type 注释：1:线下课程 2:网络课程
     * @author zoneyu 2016-10-12
     */
    private Integer recordType;
    /**
     * 表字段：app_course_system.item_remark 注释：系统课程简介
     * @author zoneyu 2016-10-12
     */
    private String itemRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }
}