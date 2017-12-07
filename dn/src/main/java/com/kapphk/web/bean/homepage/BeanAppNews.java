package com.kapphk.web.bean.homepage;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_news
 * @author zoneyu 2016-12-22
*/
public class BeanAppNews extends BaseModel {

    /**
     * 表字段：app_news.id 注释：主键id
     * @author zoneyu 2016-12-22
     */
    private Long id;
    /**
     * 表字段：app_news.logo_path 注释：图片
     * @author zoneyu 2016-12-22
     */
    private String logoPath;
    /**
     * 表字段：app_news.title 注释：标题
     * @author zoneyu 2016-12-22
     */
    private String title;
    /**
     * 表字段：app_news.link 注释：链接
     * @author zoneyu 2016-12-22
     */
    private String link;
    /**
     * 表字段：app_news.counts 注释：浏览数
     * @author zoneyu 2016-12-22
     */
    private Integer counts;
    /**
     * 表字段：app_news.likes 注释：点赞数
     * @author zoneyu 2016-12-22
     */
    private Integer likes;
    /**
     * 表字段：app_news.is_top 注释：置顶 0:否 1:是
     * @author zoneyu 2016-12-22
     */
    private Integer isTop;
    /**
     * 表字段：app_news.is_quality 注释：加精 0:否 1:是
     * @author zoneyu 2016-12-22
     */
    private Integer isQuality;
    /**
     * 表字段：app_news.record_type 注释：页面类别 1:欧爸今日说 2:学术专栏管理 3:资讯内容 4:集团内容 5:公司公告 6:动能社区动态
     * @author zoneyu 2016-12-22
     */
    private Integer recordType;
    /**
     * 表字段：app_news.news_tag 注释：资讯分类标签
     * @author zoneyu 2016-12-22
     */
    private String newsTag;
    /**
     * 表字段：app_news.tag_type 注释：标签类型
     * @author zoneyu 2016-12-22
     */
    private String tagType;
    /**
     * 表字段：app_news.is_home 注释：首页推荐 0:否 1:是
     * @author zoneyu 2016-12-22
     */
    private Integer isHome;
    /**
     * 表字段：app_news.sort 注释：排序
     * @author zoneyu 2016-12-22
     */
    private Integer sort;
    /**
     * 表字段：app_news.remark 注释：摘要
     * @author zoneyu 2016-12-22
     */
    private String remark;
    /**
     * 表字段：app_news.content 注释：内容
     * @author zoneyu 2016-12-22
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsQuality() {
        return isQuality;
    }

    public void setIsQuality(Integer isQuality) {
        this.isQuality = isQuality;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getNewsTag() {
        return newsTag;
    }

    public void setNewsTag(String newsTag) {
        this.newsTag = newsTag;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}