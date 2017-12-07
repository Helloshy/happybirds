package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * 公司：app_company
 * @author zoneyu 2016-12-06
*/
public class Company extends BaseModel {

    /**
     * 表字段：app_company.id 注释：公司id
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：app_company.company_name 注释：商家公司名
     * @author zoneyu 2016-12-06
     */
    private String companyName;
    /**
     * 表字段：app_company.province 注释：省
     * @author zoneyu 2016-12-06
     */
    private String province;
    /**
     * 表字段：app_company.city 注释：市
     * @author zoneyu 2016-12-06
     */
    private String city;
    /**
     * 表字段：app_company.district 注释：区
     * @author zoneyu 2016-12-06
     */
    private String district;
    /**
     * 表字段：app_company.address 注释：详细地址
     * @author zoneyu 2016-12-06
     */
    private String address;
    /**
     * 表字段：app_company.appid 注释：公众号AppID
     * @author zoneyu 2016-12-06
     */
    private String appid;
    /**
     * 表字段：app_company.mchid 注释：商户号
     * @author zoneyu 2016-12-06
     */
    private String mchid;
    /**
     * 表字段：app_company.apikey 注释：API密钥
     * @author zoneyu 2016-12-06
     */
    private String apikey;
    /**
     * 表字段：app_company.appSecret 注释：AppSecret(应用密钥)
     * @author zoneyu 2016-12-06
     */
    private String appsecret;
    /**
     * 表字段：app_company.file_path 注释：公众号
     * @author zoneyu 2016-12-06
     */
    private String filePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}