package com.kapphk.web.bean.system;

import java.io.Serializable;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：sys_system_user
 * @author zoneyu 2016-05-23
*/
public class BeanSystemUser extends BaseModel implements Serializable {

	private static final long serialVersionUID = 6218000453838484564L;
	/**
     * 表字段：sys_system_user.id 注释：主键id
     * @author zoneyu 2016-05-23
     */
    private Long id;
    /**
     * 表字段：sys_system_user.user_name 注释：用户名
     * @author zoneyu 2016-05-23
     */
    private String userName;
    /**
     * 表字段：sys_system_user.pwd 注释：加密密码
     * @author zoneyu 2016-05-23
     */
    private String pwd;
    /**
     * 表字段：sys_system_user.password 注释：未加密密码
     * @author zoneyu 2016-05-23
     */
    private String password;
    /**
     * 表字段：sys_system_user.name 注释：用户昵称
     * @author zoneyu 2016-05-23
     */
    private String name;
    /**
     * 表字段：sys_system_user.remark 注释：备注
     * @author zoneyu 2016-05-23
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}