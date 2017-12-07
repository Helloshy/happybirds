package com.kapphk.yyt.bean;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键ID：sys_addr_district
 * @author zoneyu 2016-12-05
*/
public class AddrDistrict extends BaseModel {

    /**
     * 表字段：sys_addr_district.id 注释：主键ID
     * @author zoneyu 2016-12-05
     */
    private String id;
    /**
     * 表字段：sys_addr_district.cid 注释：城市ID
     * @author zoneyu 2016-12-05
     */
    private String cid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}