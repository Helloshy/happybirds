package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * ：app_course_contact
 * @author zoneyu 2016-09-26
*/
public class BeanCourseContact extends BaseModel {

    /**
     * 表字段：app_course_contact.id 注释：id
     * @author zoneyu 2016-09-26
     */
    private Long id;
    /**
     * 表字段：app_course_contact.uid 注释：联系人id
     * @author zoneyu 2016-09-26
     */
    private Long uid;
    /**
     * 表字段：app_course_contact.course_id 注释：课程id
     * @author zoneyu 2016-09-26
     */
    private Long courseId;
    /**
     * 表字段：app_course_contact.logo_path 注释：用户头像
     * @author zoneyu 2016-09-26
     */
    private String logoPath;
    /**
     * 表字段：app_course_contact.user_name 注释：联系人名称
     * @author zoneyu 2016-09-26
     */
    private String userName;
    /**
     * 表字段：app_course_contact.tel 注释：联系方式
     * @author zoneyu 2016-09-26
     */
    private String tel;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
    
}