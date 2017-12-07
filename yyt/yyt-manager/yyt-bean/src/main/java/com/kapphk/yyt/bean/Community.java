package com.kapphk.yyt.bean;

import java.math.BigDecimal;

import com.kapphk.base.bean.BaseModel;

/**
 * 小区：app_community
 * @author zoneyu 2016-12-06
*/
public class Community extends BaseModel {

    /**
     * 表字段：app_community.id 注释：小区id
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：app_community.company_id 注释：所属的商家(燃气公司)ID
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    /**
     * 表字段：app_community.company_hall_id 注释：营业厅ID
     * @author zoneyu 2016-12-06
     */
    private Long companyHallId;
    /**
     * 表字段：app_community.item_name 注释：小区名称
     * @author zoneyu 2016-12-06
     */
    private String itemName;
    /**
     * 表字段：app_community.province 注释：省
     * @author zoneyu 2016-12-06
     */
    private String province;
    /**
     * 表字段：app_community.city 注释：市
     * @author zoneyu 2016-12-06
     */
    private String city;
    /**
     * 表字段：app_community.district 注释：区
     * @author zoneyu 2016-12-06
     */
    private String district;
    /**
     * 表字段：app_community.address 注释：详细地址
     * @author zoneyu 2016-12-06
     */
    private String address;
    /**
     * 表字段：app_community.sid 注释：抄表人员
     * @author zoneyu 2016-12-06
     */
    private Long sid;
    /**
     * 表字段：app_community.longitude 注释：经度
     * @author zoneyu 2016-12-06
     */
    private BigDecimal longitude;
    /**
     * 表字段：app_community.latitude 注释：纬度
     * @author zoneyu 2016-12-06
     */
    private BigDecimal latitude;
    private BigDecimal distance;

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

    public Long getCompanyHallId() {
        return companyHallId;
    }

    public void setCompanyHallId(Long companyHallId) {
        this.companyHallId = companyHallId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
    
}