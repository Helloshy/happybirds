package com.kapphk.web.bean.community;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_content_comment
 * @author zoneyu 2016-11-02
*/
public class BeanContentComment extends BaseModel {

    /**
     * 表字段：app_content_comment.id 注释：主键id
     * @author zoneyu 2016-11-02
     */
    private Long id;
    /**
     * 表字段：app_content_comment.content_id 注释：动态id
     * @author zoneyu 2016-11-02
     */
    private Long contentId;
    /**
     * 表字段：app_content_comment.uid 注释：用户id
     * @author zoneyu 2016-11-02
     */
    private Long uid;
    /**
     * 表字段：app_content_comment.content 注释：评论内容
     * @author zoneyu 2016-11-02
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}