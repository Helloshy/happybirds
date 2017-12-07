package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_tag
 * @author zoneyu 2016-09-27
*/
public class BeanCourseTag extends BaseModel {

    /**
     * 表字段：app_course_tag.id 注释：主键id
     * @author zoneyu 2016-09-27
     */
    private Long id;
    /**
     * 表字段：app_course_tag.course_id 注释：网络课程id
     * @author zoneyu 2016-09-27
     */
    private Long courseId;
    /**
     * 表字段：app_course_tag.tag_identity 注释：标签
     * @author zoneyu 2016-09-27
     */
    private String tagIdentity;
    /**
     * 表字段：app_course_tag.tag_type 注释：标签类型
     * @author zoneyu 2016-09-27
     */
    private String tagType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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