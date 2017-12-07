package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * ：sys_addr_city
 * @author zoneyu 2016-12-06
*/
public class AddrCity extends BaseModel {

    /**
     * 表字段：sys_addr_city.id 注释：
     * @author zoneyu 2016-12-06
     */
    private String id;
    /**
     * 表字段：sys_addr_city.pid 注释：省id
     * @author zoneyu 2016-12-06
     */
    private String pid;
    /**
     * 表字段：sys_addr_city.code 注释：
     * @author zoneyu 2016-12-06
     */
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}