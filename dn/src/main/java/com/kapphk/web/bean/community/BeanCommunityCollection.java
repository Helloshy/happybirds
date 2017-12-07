package com.kapphk.web.bean.community;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_community_collection
 * @author zoneyu 2016-11-10
*/
public class BeanCommunityCollection extends BaseModel {

    /**
     * 表字段：app_community_collection.id 注释：主键id
     * @author zoneyu 2016-11-10
     */
    private Long id;
    /**
     * 表字段：app_community_collection.uid 注释：用户id
     * @author zoneyu 2016-11-10
     */
    private Long uid;
    /**
     * 表字段：app_community_collection.content_id 注释：动态ID
     * @author zoneyu 2016-11-10
     */
    private Long contentId;

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

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
}