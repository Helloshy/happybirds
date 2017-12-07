package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * ：sys_resource
 * @author zoneyu 2016-05-23
*/
public class BeanResource extends BaseModel {

    /**
     * 表字段：sys_resource.id 注释：
     * @author zoneyu 2016-05-23
     */
    private Long id;
    /**
     * 表字段：sys_resource.resource_name 注释：资源名称
     * @author zoneyu 2016-05-23
     */
    private String resourceName;
    /**
     * 表字段：sys_resource.url 注释：资源路径
     * @author zoneyu 2016-05-23
     */
    private String url;
    /**
     * 表字段：sys_resource.parent_id 注释：父节点
     * @author zoneyu 2016-05-23
     */
    private Long parentId;
    /**
     * 表字段：sys_resource.type 注释：1=菜单 ；2=按钮
     * @author zoneyu 2016-05-23
     */
    private Short type;
    /**
     * 表字段：sys_resource.remark 注释：
     * @author zoneyu 2016-05-23
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}