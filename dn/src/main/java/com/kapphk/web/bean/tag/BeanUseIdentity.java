package com.kapphk.web.bean.tag;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_identity
 * @author zoneyu 2016-09-22
*/
public class BeanUseIdentity extends BaseModel {

    /**
     * 表字段：user_identity.id 注释：主键id
     * @author zoneyu 2016-09-22
     */
    private Long id;
    /**
     * 表字段：user_identity.uid 注释：用户id
     * @author zoneyu 2016-09-22
     */
    private Long uid;
    /**
     * 表字段：user_identity.tag_identity 注释：个人身份
     * @author zoneyu 2016-09-22
     */
    private String tagIdentity;
    /**
     * 表字段：user_identity.tag_type 注释：标签类型
     * @author zoneyu 2016-09-22
     */
    private String tagType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTagIdentity() {
        return tagIdentity;
    }

    public void setTagIdentity(String tagIdentity) {
        this.tagIdentity = tagIdentity;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}