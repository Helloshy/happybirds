package com.kapphk.web.bean.user;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_collection
 * @author zoneyu 2016-10-09
*/
public class BeanUserCollection extends BaseModel {

    /**
     * 表字段：user_collection.id 注释：主键id
     * @author zoneyu 2016-10-09
     */
    private Long id;
    /**
     * 表字段：user_collection.news_id 注释：收藏文章id
     * @author zoneyu 2016-10-09
     */
    private Long newsId;
    /**
     * 表字段：user_collection.uid 注释：用户id
     * @author zoneyu 2016-10-09
     */
    private Long uid;
    /**
     * 表字段：user_collection.record_type 注释：页面类别 1:欧爸今日说 2:学术专栏管理 3:资讯内容 4:集团内容 5:公司公告
     * @author zoneyu 2016-10-09
     */
    private Integer recordType;
    /**
     * 表字段：user_collection.data_type 注释：类型 1:收藏 2:点赞
     * @author zoneyu 2016-10-09
     */
    private Integer dataType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}