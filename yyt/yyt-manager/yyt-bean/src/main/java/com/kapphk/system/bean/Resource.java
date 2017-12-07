package com.kapphk.system.bean;

import java.util.List;

import com.kapphk.base.bean.BaseModel;


public class Resource extends BaseModel {

    private Long id;
    /** 资源名称 */
    private String resourceName;

    /** 资源路径 */
    private String url;

    /** 父节点 */
    private Long parentId;

    /** 1=菜单 ；2=按钮 */
    private Integer type;

    /** remark */
    private String remark;

    /** permission */
    private String permission;

    /** icon */
    private String icon;

    /** btn */
    private String btn;

    private List<Resource> menus;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 资源名称 的值
     * @return String
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置资源名称 的值
     * @param  resourceName
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * 获取 资源路径 的值
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源路径 的值
     * @param  url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取 父节点 的值
     * @return Long
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父节点 的值
     * @param  parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 1=菜单 ；2=按钮 的值
     * @return Integer
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1=菜单 ；2=按钮 的值
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取 remark 的值
     * @return String
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark 的值
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 permission 的值
     * @return String
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置permission 的值
     * @param  permission
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取 icon 的值
     * @return String
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置icon 的值
     * @param  icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取 btn 的值
     * @return String
     */
    public String getBtn() {
        return btn;
    }

    /**
     * 设置btn 的值
     * @param  btn
     */
    public void setBtn(String btn) {
        this.btn = btn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Resource> getMenus() {
        return menus;
    }

    public void setMenus(List<Resource> menus) {
        this.menus = menus;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName());
        sb.append("; id=" + (id == null ? "null" : id.toString()));
        sb.append("; resourceName=" + (resourceName == null ? "null" : resourceName.toString()));
        sb.append("; url=" + (url == null ? "null" : url.toString()));
        sb.append("; parentId=" + (parentId == null ? "null" : parentId.toString()));
        sb.append("; type=" + (type == null ? "null" : type.toString()));
        sb.append("; remark=" + (remark == null ? "null" : remark.toString()));
        sb.append("; permission=" + (permission == null ? "null" : permission.toString()));
        sb.append("; icon=" + (icon == null ? "null" : icon.toString()));
        sb.append("; btn=" + (btn == null ? "null" : btn.toString()));

        return sb.toString();
    }
}