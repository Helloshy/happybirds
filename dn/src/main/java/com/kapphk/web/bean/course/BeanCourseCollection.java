package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_collection
 * @author zoneyu 2016-10-12
*/
public class BeanCourseCollection extends BaseModel {

    /**
     * 表字段：app_course_collection.id 注释：主键id
     * @author zoneyu 2016-10-12
     */
    private Long id;
    /**
     * 表字段：app_course_collection.uid 注释：用户id
     * @author zoneyu 2016-10-12
     */
    private Long uid;
    /**
     * 表字段：app_course_collection.course_id 注释：课程id
     * @author zoneyu 2016-10-12
     */
    private Long courseId;
    /**
     * 表字段：app_course_collection.collection_type 注释：收藏类型（1：课程）
     * @author zoneyu 2016-10-12
     */
    private Integer collectionType;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(Integer collectionType) {
        this.collectionType = collectionType;
    }
}