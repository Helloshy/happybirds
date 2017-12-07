package com.kapphk.web.bean.community;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_community_staff
 * @author zoneyu 2016-10-31
*/
public class BeanCommunityStaff extends BaseModel {

    /**
     * 表字段：app_community_staff.id 注释：主键id
     * @author zoneyu 2016-10-31
     */
    private Long id;
    /**
     * 表字段：app_community_staff.community_id 注释：社区id
     * @author zoneyu 2016-10-31
     */
    private Long communityId;
    /**
     * 表字段：app_community_staff.tag_value 注释：社区身份值
     * @author zoneyu 2016-10-31
     */
    private String tagValue;
    /**
     * 表字段：app_community_staff.tag_type 注释：标签类型: 社区身份
     * @author zoneyu 2016-10-31
     */
    private String tagType;
    /**
     * 表字段：app_community_staff.uid 注释：用户id
     * @author zoneyu 2016-10-31
     */
    private Long uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}