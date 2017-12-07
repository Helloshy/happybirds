package com.kapphk.web.bean.course;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * ：app_course_teacher
 * @author zoneyu 2016-09-26
*/
public class BeanCourseTeacher extends BaseModel {

    /**
     * 表字段：app_course_teacher.id 注释：id
     * @author zoneyu 2016-09-26
     */
    private Long id;
    /**
     * 表字段：app_course_teacher.course_id 注释：课程id
     * @author zoneyu 2016-09-26
     */
    private Long courseId;
    /**
     * 表字段：app_course_teacher.teacher_id 注释：教师id
     * @author zoneyu 2016-09-26
     */
    private Long teacherId;
    /**
     * 表字段：app_course_teacher.logo_path 注释：用户头像
     * @author zoneyu 2016-09-26
     */
    private String logoPath;
    /**
     * 表字段：app_course_teacher.user_name 注释：讲师名称
     * @author zoneyu 2016-09-26
     */
    private String userName;
    /**
     * 表字段：app_course_teacher.remark 注释：简介
     * @author zoneyu 2016-09-26
     */
    private String remark;
    /**
     * 表字段：app_course_teacher.salary 注释：讲师工资
     * @author zoneyu 2016-09-26
     */
    private BigDecimal salary;
    /**
     * 表字段：app_course_teacher.teach_times 注释：讲课次数
     * @author zoneyu 2016-09-26
     */
    private Integer teachTimes;
    /**
     * 表字段：app_course_teacher.rate 注释：线下分成
     * @author zoneyu 2016-09-26
     */
    private BigDecimal rate;

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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getTeachTimes() {
        return teachTimes;
    }

    public void setTeachTimes(Integer teachTimes) {
        this.teachTimes = teachTimes;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
    
}