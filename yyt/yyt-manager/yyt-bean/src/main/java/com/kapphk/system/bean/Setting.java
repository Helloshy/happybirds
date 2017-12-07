package com.kapphk.system.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键：sys_setting
 * @author zoneyu 2016-09-28
*/
public class Setting extends BaseModel {

    /**
     * 表字段：sys_setting.id 注释：主键id
     * @author zoneyu 2016-09-28
     */
    private String id;
    /**
     * 表字段：sys_setting.company_name 注释：公司名称
     * @author zoneyu 2016-09-28
     */
    private String companyName;
    /**
     * 表字段：sys_setting.company_tel 注释：公司电话
     * @author zoneyu 2016-09-28
     */
    private String companyTel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }
}