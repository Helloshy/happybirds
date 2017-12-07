package com.kapphk.web.bean;


/**
 * ：sys_phone_code
 * @author zoneyu 2016-05-24
*/
public class BeanPhoneCode extends BaseModel {

    /**
     * 表字段：sys_phone_code.id 注释：
     * @author zoneyu 2016-05-24
     */
    private Long id;
    /**
     * 表字段：sys_phone_code.phone 注释：用户注册手机号码
     * @author zoneyu 2016-05-24
     */
    private String phone;
    /**
     * 表字段：sys_phone_code.code 注释：验证码
     * @author zoneyu 2016-05-24
     */
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}