package com.kapphk.web.bean.homepage;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：sys_guide
 * @author zoneyu 2016-12-22
*/
public class BeanGuide extends BaseModel {

    /**
     * 表字段：sys_guide.id 注释：主键id
     * @author zoneyu 2016-12-22
     */
    private Long id;
    /**
     * 表字段：sys_guide.item_link 注释：连接
     * @author zoneyu 2016-12-22
     */
    private String itemLink;
    /**
     * 表字段：sys_guide.logo_path 注释：图片路径
     * @author zoneyu 2016-12-22
     */
    private String logoPath;
    /**
     * 表字段：sys_guide.teacher_id 注释：讲师id
     * @author zoneyu 2016-12-22
     */
    private Long teacherId;
    /**
     * 表字段：sys_guide.record_type 注释：类型 1:首页 2:动能名师 3:动能集团 4:动能社区
     * @author zoneyu 2016-12-22
     */
    private Integer recordType;
    /**
     * 表字段：sys_guide.sort 注释：排序
     * @author zoneyu 2016-12-22
     */
    private Integer sort;
    /**
     * 表字段：sys_guide.state 注释：是否有效（1：有效；0：无效）
     * @author zoneyu 2016-12-22
     */
    private Integer state;
    /**
     * 表字段：sys_guide.content 注释：内容
     * @author zoneyu 2016-12-22
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}