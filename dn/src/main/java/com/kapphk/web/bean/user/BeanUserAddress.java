package com.kapphk.web.bean.user;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键ID：app_user_address
 * @author zoneyu 2016-12-03
*/
public class BeanUserAddress extends BaseModel {

    /**
     * 表字段：app_user_address.id 注释：主键ID
     * @author zoneyu 2016-12-03
     */
    private Long id;
    /**
     * 表字段：app_user_address.uid 注释：用户ID
     * @author zoneyu 2016-12-03
     */
    private Long uid;
    /**
     * 表字段：app_user_address.province 注释：省
     * @author zoneyu 2016-12-03
     */
    private String province;
    /**
     * 表字段：app_user_address.city 注释：市
     * @author zoneyu 2016-12-03
     */
    private String city;
    /**
     * 表字段：app_user_address.district 注释：区
     * @author zoneyu 2016-12-03
     */
    private String district;
    /**
     * 表字段：app_user_address.address 注释：详细地址
     * @author zoneyu 2016-12-03
     */
    private String address;
    /**
     * 表字段：app_user_address.phone 注释：手机
     * @author zoneyu 2016-12-03
     */
    private String phone;
    /**
     * 表字段：app_user_address.name 注释：收货人姓名
     * @author zoneyu 2016-12-03
     */
    private String name;
    /**
     * 表字段：app_user_address.is_default 注释：是否默认(0：否，1：是)
     * @author zoneyu 2016-12-03
     */
    private Integer isDefault;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}