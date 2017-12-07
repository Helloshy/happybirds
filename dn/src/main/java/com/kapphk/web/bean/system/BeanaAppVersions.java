package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_versions
 * @author zoneyu 2016-12-12
*/
public class BeanaAppVersions extends BaseModel {

    /**
     * 表字段：app_versions.id 注释：主键
     * @author zoneyu 2016-12-12
     */
    private Long id;
    /**
     * 表字段：app_versions.item_name 注释：版本名称
     * @author zoneyu 2016-12-12
     */
    private String itemName;
    /**
     * 表字段：app_versions.remark 注释：备注
     * @author zoneyu 2016-12-12
     */
    private String remark;
    /**
     * 表字段：app_versions.path 注释：下载路径
     * @author zoneyu 2016-12-12
     */
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}