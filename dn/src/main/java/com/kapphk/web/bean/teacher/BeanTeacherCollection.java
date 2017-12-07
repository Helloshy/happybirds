package com.kapphk.web.bean.teacher;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_teacher_collection
 * @author zoneyu 2016-10-14
*/
public class BeanTeacherCollection extends BaseModel {

    /**
     * 表字段：app_teacher_collection.id 注释：主键id
     * @author zoneyu 2016-10-14
     */
    private Long id;
    /**
     * 表字段：app_teacher_collection.uid 注释：用户id
     * @author zoneyu 2016-10-14
     */
    private Long uid;
    /**
     * 表字段：app_teacher_collection.teacher_id 注释：老师/陪伴师id
     * @author zoneyu 2016-10-14
     */
    private Long teacherId;
    /**
     * 表字段：app_teacher_collection.collection_type 注释：收藏类型 1:老师 2:陪伴师
     * @author zoneyu 2016-10-14
     */
    private Integer collectionType;
    /**
     * 表字段：app_teacher_collection.data_type 注释：类型 1:收藏 2:点赞
     * @author zoneyu 2016-10-14
     */
    private Integer dataType;

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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(Integer collectionType) {
        this.collectionType = collectionType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}