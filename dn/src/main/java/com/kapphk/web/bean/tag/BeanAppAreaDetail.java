package com.kapphk.web.bean.tag;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_area_detail
 * @author zoneyu 2016-10-28
*/
public class BeanAppAreaDetail extends BaseModel {

    /**
     * 表字段：app_area_detail.id 注释：主键id
     * @author zoneyu 2016-10-28
     */
    private Long id;
    /**
     * 表字段：app_area_detail.area_id 注释：区域id
     * @author zoneyu 2016-10-28
     */
    private String areaId;
    /**
     * 表字段：app_area_detail.pid 注释：省id
     * @author zoneyu 2016-10-28
     */
    private String pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}