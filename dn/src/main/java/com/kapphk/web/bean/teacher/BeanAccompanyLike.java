package com.kapphk.web.bean.teacher;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_accompany_like
 * @author zoneyu 2016-10-10
*/
public class BeanAccompanyLike extends BaseModel {

    /**
     * 表字段：app_accompany_like.id 注释：主键id
     * @author zoneyu 2016-10-10
     */
    private Long id;
    /**
     * 表字段：app_accompany_like.accompany_id 注释：陪伴师id
     * @author zoneyu 2016-10-10
     */
    private Long accompanyId;
    /**
     * 表字段：app_accompany_like.uid 注释：用户id
     * @author zoneyu 2016-10-10
     */
    private Long uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccompanyId() {
        return accompanyId;
    }

    public void setAccompanyId(Long accompanyId) {
        this.accompanyId = accompanyId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}