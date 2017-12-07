package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键ID：app_join
 * @author zoneyu 2016-10-22
*/
public class BeanAppJoin extends BaseModel {

    /**
     * 表字段：app_join.id 注释：主键ID
     * @author zoneyu 2016-10-22
     */
    private Long id;
    /**
     * 表字段：app_join.nickName 注释：姓名
     * @author zoneyu 2016-10-22
     */
    private String nickname;
    /**
     * 表字段：app_join.phone 注释：联系电话
     * @author zoneyu 2016-10-22
     */
    private String phone;
    /**
     * 表字段：app_join.conent 注释：加盟意向
     * @author zoneyu 2016-10-22
     */
    private String conent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }
}