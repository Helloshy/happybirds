package com.kapphk.web.bean.teacher;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_accompany
 * @author zoneyu 2016-10-10
*/
public class BeanAppAccompany extends BaseModel {

    /**
     * 表字段：app_accompany.id 注释：主键id
     * @author zoneyu 2016-10-10
     */
    private Long id;
    /**
     * 表字段：app_accompany.teacher_id 注释：陪伴师id
     * @author zoneyu 2016-10-10
     */
    private Long teacherId;
    /**
     * 表字段：app_accompany.remark 注释：陪伴描述
     * @author zoneyu 2016-10-10
     */
    private String remark;
    /**
     * 表字段：app_accompany.logo_path 注释：头像路径
     * @author zoneyu 2016-10-10
     */
    private String logoPath;
    /**
     * 表字段：app_accompany.logo_path2 注释：头像路径2
     * @author zoneyu 2016-10-10
     */
    private String logoPath2;
    /**
     * 表字段：app_accompany.logo_path3 注释：头像路径3
     * @author zoneyu 2016-10-10
     */
    private String logoPath3;

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

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoPath2() {
        return logoPath2;
    }

    public void setLogoPath2(String logoPath2) {
        this.logoPath2 = logoPath2;
    }

    public String getLogoPath3() {
        return logoPath3;
    }

    public void setLogoPath3(String logoPath3) {
        this.logoPath3 = logoPath3;
    }
}