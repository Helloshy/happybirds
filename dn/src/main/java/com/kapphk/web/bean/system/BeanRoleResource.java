package com.kapphk.web.bean.system;

import com.kapphk.web.bean.BaseModel;

/**
 * ：sys_role_resource
 * @author zoneyu 2016-05-23
*/
public class BeanRoleResource extends BaseModel {

    /**
     * 表字段：sys_role_resource.id 注释：
     * @author zoneyu 2016-05-23
     */
    private Long id;
    /**
     * 表字段：sys_role_resource.resource_id 注释：资源ID
     * @author zoneyu 2016-05-23
     */
    private Long resourceId;
    /**
     * 表字段：sys_role_resource.role_id 注释：角色ID
     * @author zoneyu 2016-05-23
     */
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}