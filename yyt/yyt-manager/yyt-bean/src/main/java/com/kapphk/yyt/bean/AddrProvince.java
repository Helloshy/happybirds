package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键ID：sys_addr_province
 * @author zoneyu 2016-12-06
*/
public class AddrProvince extends BaseModel {

    /**
     * 表字段：sys_addr_province.id 注释：主键ID
     * @author zoneyu 2016-12-06
     */
    private String id;
    /**
     * 表字段：sys_addr_province.code 注释：省份代码
     * @author zoneyu 2016-12-06
     */
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}