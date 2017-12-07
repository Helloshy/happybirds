package com.kapphk.yyt.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.base.bean.BaseModel;

/**
 * ID：app_company_hall
 * @author zoneyu 2016-12-06
*/
public class CompanyHall extends BaseModel {

    /**
     * 表字段：app_company_hall.id 注释：ID
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：app_company_hall.company_id 注释：商家ID
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    /**
     * 表字段：app_company_hall.hall_name 注释：营业网点名称
     * @author zoneyu 2016-12-06
     */
    private String hallName;
    /**
     * 表字段：app_company_hall.manager 注释：负责人
     * @author zoneyu 2016-12-06
     */
    private String manager;
    /**
     * 表字段：app_company_hall.tel 注释：联系电话
     * @author zoneyu 2016-12-06
     */
    private String tel;
    /**
     * 表字段：app_company_hall.province 注释：省
     * @author zoneyu 2016-12-06
     */
    private String province;
    /**
     * 表字段：app_company_hall.city 注释：市
     * @author zoneyu 2016-12-06
     */
    private String city;
    /**
     * 表字段：app_company_hall.district 注释：区
     * @author zoneyu 2016-12-06
     */
    private String district;
    /**
     * 表字段：app_company_hall.address 注释：详细地址
     * @author zoneyu 2016-12-06
     */
    private String address;
    /**
     * 表字段：app_company_hall.longitude 注释：经度
     * @author zoneyu 2016-12-06
     */
    private BigDecimal longitude;
    /**
     * 表字段：app_company_hall.latitude 注释：纬度
     * @author zoneyu 2016-12-06
     */
    private BigDecimal latitude;
    /**
     * 表字段：app_company_hall.update_by 注释：操作人
     * @author zoneyu 2016-12-06
     */
    private Long updateBy;
    /**
     * 表字段：app_company_hall.update_time 注释：更新时间
     * @author zoneyu 2016-12-06
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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
}