package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键ID：app_join
 * @author zoneyu 2016-10-22
*/
public class BeanJoin extends BaseModel {

    /**
     * 表字段：app_join.id 注释：主键ID
     * @author zoneyu 2016-10-22
     */
    private Long id;
    /**
     * 表字段：app_join.nick_name 注释：姓名
     * @author zoneyu 2016-10-22
     */
    private String nickName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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