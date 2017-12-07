package com.kapphk.system.bean;

import com.kapphk.base.bean.BaseModel;
import java.util.Date;

/**
 * 用户ID：sys_user
 * @author zoneyu 2016-12-06
*/
public class User extends BaseModel {

    /**
     * 表字段：sys_user.id 注释：用户ID
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：sys_user.username 注释：用户账号
     * @author zoneyu 2016-12-06
     */
    private String username;
    /**
     * 表字段：sys_user.pwd 注释：密码
     * @author zoneyu 2016-12-06
     */
    private String pwd;
    /**
     * 表字段：sys_user.realname 注释：真实姓名
     * @author zoneyu 2016-12-06
     */
    private String realname;
    /**
     * 表字段：sys_user.phone 注释：手机号
     * @author zoneyu 2016-12-06
     */
    private String phone;
    /**
     * 表字段：sys_user.sex 注释：性别 1:男 2:女
     * @author zoneyu 2016-12-06
     */
    private Integer sex;
    /**
     * 表字段：sys_user.wechat 注释：微信号
     * @author zoneyu 2016-12-06
     */
    private String wechat;
    /**
     * 表字段：sys_user.headimgurl 注释：微信用户头像
     * @author zoneyu 2016-12-06
     */
    private String headimgurl;
    /**
     * 表字段：sys_user.openid 注释：微信用户的OPENID
     * @author zoneyu 2016-12-06
     */
    private String openid;
    /**
     * 表字段：sys_user.state 注释：是否有效（1：有效；0：无效）
     * @author zoneyu 2016-12-06
     */
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}