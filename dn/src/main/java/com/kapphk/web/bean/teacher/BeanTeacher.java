package com.kapphk.web.bean.teacher;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_teacher
 * @author zoneyu 2016-12-22
*/
public class BeanTeacher extends BaseModel {

    /**
     * 表字段：app_teacher.id 注释：主键id
     * @author zoneyu 2016-12-22
     */
    private Long id;
    /**
     * 表字段：app_teacher.logo_path 注释：图片
     * @author zoneyu 2016-12-22
     */
    private String logoPath;
    /**
     * 表字段：app_teacher.uid 注释：陪伴师id
     * @author zoneyu 2016-12-22
     */
    private Long uid;
    /**
     * 表字段：app_teacher.teacher_level 注释：级别
     * @author zoneyu 2016-12-22
     */
    private String teacherLevel;
    /**
     * 表字段：app_teacher.item_name 注释：讲师名称
     * @author zoneyu 2016-12-22
     */
    private String itemName;
    /**
     * 表字段：app_teacher.item_remark 注释：讲师简介
     * @author zoneyu 2016-12-22
     */
    private String itemRemark;
    /**
     * 表字段：app_teacher.item_theme 注释：擅长主题
     * @author zoneyu 2016-12-22
     */
    private String itemTheme;
    /**
     * 表字段：app_teacher.record_type 注释：类型 1:动能名师 2:陪伴师
     * @author zoneyu 2016-12-22
     */
    private Integer recordType;
    /**
     * 表字段：app_teacher.tag_value 注释：擅长学科
     * @author zoneyu 2016-12-22
     */
    private String tagValue;
    /**
     * 表字段：app_teacher.tag_type 注释：tag类型
     * @author zoneyu 2016-12-22
     */
    private String tagType;
    /**
     * 表字段：app_teacher.sort 注释：排序
     * @author zoneyu 2016-12-22
     */
    private Integer sort;
    /**
     * 表字段：app_teacher.is_home 注释：首页推荐 0:否 1:是
     * @author zoneyu 2016-12-22
     */
    private Integer isHome;
    /**
     * 表字段：app_teacher.item_content 注释：课程介绍
     * @author zoneyu 2016-12-22
     */
    private String itemContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(String teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemTheme() {
        return itemTheme;
    }

    public void setItemTheme(String itemTheme) {
        this.itemTheme = itemTheme;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
}