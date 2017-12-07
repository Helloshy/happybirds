package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_apply
 * @author zoneyu 2016-09-21
*/
public class BeanUserApply extends BaseModel {

    /**
     * 表字段：user_apply.id 注释：主键id
     * @author zoneyu 2016-09-21
     */
    private Long id;
    /**
     * 表字段：user_apply.record_type 注释：申请来源 1:动能留学-提交申请 2:财商课程-合作加盟 3:动能社区-社区申请 4:集团介绍-合作加盟 5:集团介绍-招聘信息
     * @author zoneyu 2016-09-21
     */
    private Long recordType;
    /**
     * 表字段：user_apply.user_name 注释：姓名
     * @author zoneyu 2016-09-21
     */
    private String userName;
    /**
     * 表字段：user_apply.user_tel 注释：联系电话
     * @author zoneyu 2016-09-21
     */
    private String userTel;
    /**
     * 表字段：user_apply.province 注释：省
     * @author zoneyu 2016-09-21
     */
    private String province;
    /**
     * 表字段：user_apply.city 注释：市
     * @author zoneyu 2016-09-21
     */
    private String city;
    /**
     * 表字段：user_apply.district 注释：区
     * @author zoneyu 2016-09-21
     */
    private String district;
    /**
     * 表字段：user_apply.content 注释：申请留言
     * @author zoneyu 2016-09-21
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordType() {
        return recordType;
    }

    public void setRecordType(Long recordType) {
        this.recordType = recordType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}