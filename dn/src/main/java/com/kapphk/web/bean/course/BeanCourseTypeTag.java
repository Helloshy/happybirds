package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_type_tag
 * @author zoneyu 2016-09-23
*/
public class BeanCourseTypeTag extends BaseModel {

    /**
     * 表字段：app_course_type_tag.id 注释：主键id
     * @author zoneyu 2016-09-23
     */
    private Long id;
    /**
     * 表字段：app_course_type_tag.course_type_id 注释：课程系列id
     * @author zoneyu 2016-09-23
     */
    private Long courseTypeId;
    /**
     * 表字段：app_course_type_tag.tag_identity 注释：标签
     * @author zoneyu 2016-09-23
     */
    private String tagIdentity;
    /**
     * 表字段：app_course_type_tag.tag_type 注释：标签类型
     * @author zoneyu 2016-09-23
     */
    private String tagType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getTagIdentity() {
        return tagIdentity;
    }

    public void setTagIdentity(String tagIdentity) {
        this.tagIdentity = tagIdentity;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}