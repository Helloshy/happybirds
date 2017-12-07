package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_student
 * @author zoneyu 2016-10-19
*/
public class BeanCourseStudent extends BaseModel {

    /**	
     * 表字段：app_course_student.id 注释：主键id
     * @author zoneyu 2016-10-19
     */
    private Long id;
    /**
     * 表字段：app_course_student.parent_name 注释：第一联系人-姓名
     * @author zoneyu 2016-10-19
     */
    private String parentName;
    /**
     * 表字段：app_course_student.parent_relation 注释：第一联系人-关系
     * @author zoneyu 2016-10-19
     */
    private String parentRelation;
    /**
     * 表字段：app_course_student.parent_phone 注释：第一联系人-手机号
     * @author zoneyu 2016-10-19
     */
    private String parentPhone;
    /**
     * 表字段：app_course_student.parent_name2 注释：第二联系人-姓名
     * @author zoneyu 2016-10-19
     */
    private String parentName2;
    /**
     * 表字段：app_course_student.parent_relation2 注释：第二联系人-关系
     * @author zoneyu 2016-10-19
     */
    private String parentRelation2;
    /**
     * 表字段：app_course_student.parent_phone2 注释：第二联系人-手机号
     * @author zoneyu 2016-10-19
     */
    private String parentPhone2;
    /**
     * 表字段：app_course_student.student_name 注释：孩子姓名
     * @author zoneyu 2016-10-19
     */
    private String studentName;
    /**
     * 表字段：app_course_student.sex 注释：性别 1:男 2:女
     * @author zoneyu 2016-10-19
     */
    private Integer sex;
    /**
     * 表字段：app_course_student.student_id_card 注释：身份证号
     * @author zoneyu 2016-10-19
     */
    private String studentIdCard;
    /**
     * 表字段：app_course_student.body_status 注释：身体状况
     * @author zoneyu 2016-10-19
     */
    private String bodyStatus;
    /**
     * 表字段：app_course_student.case_history 注释：以往病史
     * @author zoneyu 2016-10-19
     */
    private String caseHistory;
    /**
     * 表字段：app_course_student.remark 注释：备注
     * @author zoneyu 2016-10-19
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentRelation() {
        return parentRelation;
    }

    public void setParentRelation(String parentRelation) {
        this.parentRelation = parentRelation;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getParentName2() {
        return parentName2;
    }

    public void setParentName2(String parentName2) {
        this.parentName2 = parentName2;
    }

    public String getParentRelation2() {
        return parentRelation2;
    }

    public void setParentRelation2(String parentRelation2) {
        this.parentRelation2 = parentRelation2;
    }

    public String getParentPhone2() {
        return parentPhone2;
    }

    public void setParentPhone2(String parentPhone2) {
        this.parentPhone2 = parentPhone2;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(String studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public String getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(String bodyStatus) {
        this.bodyStatus = bodyStatus;
    }

    public String getCaseHistory() {
        return caseHistory;
    }

    public void setCaseHistory(String caseHistory) {
        this.caseHistory = caseHistory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}