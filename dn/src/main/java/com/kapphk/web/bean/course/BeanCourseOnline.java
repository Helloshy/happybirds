package com.kapphk.web.bean.course;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_online
 * @author zoneyu 2016-09-23
*/
public class BeanCourseOnline extends BaseModel {

    /**
     * 表字段：app_course_online.id 注释：主键id
     * @author zoneyu 2016-09-23
     */
    private Long id;
    /**
     * 表字段：app_course_online.item_remark 注释：课程简介
     * @author zoneyu 2016-09-23
     */
    private String itemRemark;
    /**
     * 表字段：app_course_online.course_group 注释：课程分类
     * @author zoneyu 2016-09-23
     */
    private String courseGroup;
    /**
     * 表字段：app_course_online.course_type 注释：课程类型 1:视频 2:音频
     * @author zoneyu 2016-09-23
     */
    private Integer courseType;
    /**
     * 表字段：app_course_online.vedio_link 注释：视频网址
     * @author zoneyu 2016-09-23
     */
    private String vedioLink;
    /**
     * 表字段：app_course_online.vedio_palys 注释：播放次数
     * @author zoneyu 2016-09-23
     */
    private Integer vedioPalys;
    /**
     * 表字段：app_course_online.teacher_id 注释：老师id
     * @author zoneyu 2016-09-23
     */
    private Long teacherId;
    /**
     * 表字段：app_course_online.teacher_logo_path 注释：用户头像
     * @author zoneyu 2016-09-23
     */
    private String teacherLogoPath;
    /**
     * 表字段：app_course_online.teacher_name 注释：讲师名称
     * @author zoneyu 2016-09-23
     */
    private String teacherName;
    /**
     * 表字段：app_course_online.teacher_remark 注释：简介
     * @author zoneyu 2016-09-23
     */
    private String teacherRemark;
    /**
     * 表字段：app_course_online.rate 注释：课程销售分成
     * @author zoneyu 2016-09-23
     */
    private BigDecimal rate;
    /**
     * 表字段：app_course_online.course_content 注释：课程介绍
     * @author zoneyu 2016-09-23
     */
    private String courseContent;
    private Integer linkType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getVedioLink() {
        return vedioLink;
    }

    public void setVedioLink(String vedioLink) {
        this.vedioLink = vedioLink;
    }

    public Integer getVedioPalys() {
        return vedioPalys;
    }

    public void setVedioPalys(Integer vedioPalys) {
        this.vedioPalys = vedioPalys;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherLogoPath() {
        return teacherLogoPath;
    }

    public void setTeacherLogoPath(String teacherLogoPath) {
        this.teacherLogoPath = teacherLogoPath;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherRemark() {
        return teacherRemark;
    }

    public void setTeacherRemark(String teacherRemark) {
        this.teacherRemark = teacherRemark;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}
    
}