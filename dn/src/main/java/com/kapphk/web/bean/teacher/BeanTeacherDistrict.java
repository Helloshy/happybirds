package com.kapphk.web.bean.teacher;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_teacher_district
 * @author zoneyu 2016-10-08
*/
public class BeanTeacherDistrict extends BaseModel {

    /**O
     * 表字段：app_teacher_district.id 注释：主键id
     * @author zoneyu 2016-10-08
     */
    private Long id;
    /**
     * 表字段：app_teacher_district.teacher_id 注释：教师id
     * @author zoneyu 2016-10-08
     */
    private Long teacherId;
    /**
     * 表字段：app_teacher_district.tag_value 注释：标签
     * @author zoneyu 2016-10-08
     */
    private String tagValue;
    /**
     * 表字段：app_teacher_district.tag_type 注释：标签类型
     * @author zoneyu 2016-10-08
     */
    private String tagType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}