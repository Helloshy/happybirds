package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键ID：sys_addr_province
 * @author zoneyu 2016-07-25
*/
public class BeanProvince extends BaseModel {

    /**
     * 表字段：sys_addr_province.id 注释：主键ID
     * @author zoneyu 2016-07-25
     */
    private Long id;
    /**
     * 表字段：sys_addr_province.name 注释：省份
     * @author zoneyu 2016-07-25
     */
    private String name;
    /**
     * 表字段：sys_addr_province.code 注释：省份代码
     * @author zoneyu 2016-07-25
     */
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}