package com.kapphk.system.bean;

import com.kapphk.base.bean.BaseModel;
import java.util.Date;

/**
 * 主键：sys_system_user
 * @author zoneyu 2016-12-06
*/
public class SystemUser extends BaseModel {

    /**
     * 表字段：sys_system_user.id 注释：主键id
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：sys_system_user.user_name 注释：用户名
     * @author zoneyu 2016-12-06
     */
    private String userName;
    /**
     * 表字段：sys_system_user.pwd 注释：密码
     * @author zoneyu 2016-12-06
     */
    private String pwd;
    /**
     * 表字段：sys_system_user.real_name 注释：员工姓名
     * @author zoneyu 2016-12-06
     */
    private String realName;
    /**
     * 表字段：sys_system_user.company_id 注释：燃气公司ID
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    /**
     * 表字段：sys_system_user.department_tag 注释：部门id
     * @author zoneyu 2016-12-06
     */
    private String departmentTag;
    /**
     * 表字段：sys_system_user.tag_type 注释：标签类型:部门
     * @author zoneyu 2016-12-06
     */
    private String tagType;
    /**
     * 表字段：sys_system_user.position 注释：职位
     * @author zoneyu 2016-12-06
     */
    private String position;
    /**
     * 表字段：sys_system_user.tel 注释：联系方式
     * @author zoneyu 2016-12-06
     */
    private String tel;

    /**
     * 表字段：sys_system_user.update_by 注释：操作人
     * @author zoneyu 2016-12-06
     */
    private Long updateBy;
    /**
     * 表字段：sys_system_user.update_time 注释：更新时间
     * @author zoneyu 2016-12-06
     */
    private Date updateTime;
    /**
     * 表字段：sys_system_user.salt 注释：
     * @author zoneyu 2016-12-06
     */
    private String salt;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDepartmentTag() {
        return departmentTag;
    }

    public void setDepartmentTag(String departmentTag) {
        this.departmentTag = departmentTag;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}