package com.kapphk.web.bean.community;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_community_content
 * @author zoneyu 2016-10-31
*/
public class BeanCommunityContent extends BaseModel {

    /**
     * 表字段：app_community_content.id 注释：主键id
     * @author zoneyu 2016-10-31
     */
    private Long id;
    /**
     * 表字段：app_community_content.community_id 注释：社区id
     * @author zoneyu 2016-10-31
     */
    private Long communityId;
    /**
     * 表字段：app_community_content.record_type 注释：类别 1:公告 2:动态
     * @author zoneyu 2016-10-31
     */
    private Integer recordType;
    /**
     * 表字段：app_community_content.logo_path 注释：图片
     * @author zoneyu 2016-10-31
     */
    private String logoPath;
    /**
     * 表字段：app_community_content.logo_path2 注释：图片2
     * @author zoneyu 2016-10-31
     */
    private String logoPath2;
    /**
     * 表字段：app_community_content.logo_path3 注释：图片3
     * @author zoneyu 2016-10-31
     */
    private String logoPath3;
    /**
     * 表字段：app_community_content.likes 注释：点赞数
     * @author zoneyu 2016-10-31
     */
    private Integer likes;
    /**
     * 表字段：app_community_content.is_report 注释：是否被举报 0:否 1:是
     * @author zoneyu 2016-10-31
     */
    private Integer isReport;
    /**
     * 表字段：app_community_content.uid 注释：发布人 -1:动能集团官方
     * @author zoneyu 2016-10-31
     */
    private Long uid;
    /**
     * 表字段：app_community_content.content 注释：内容
     * @author zoneyu 2016-10-31
     */
    private String content;
    /**
     * 表字段：app_community_content.icon_value 注释：身份图标
     * @author zoneyu 2016-10-31
     */
    private String iconValue;

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

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoPath2() {
        return logoPath2;
    }

    public void setLogoPath2(String logoPath2) {
        this.logoPath2 = logoPath2;
    }

    public String getLogoPath3() {
        return logoPath3;
    }

    public void setLogoPath3(String logoPath3) {
        this.logoPath3 = logoPath3;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getIsReport() {
        return isReport;
    }

    public void setIsReport(Integer isReport) {
        this.isReport = isReport;
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

	public String getIconValue() {
		return iconValue;
	}

	public void setIconValue(String iconValue) {
		this.iconValue = iconValue;
	}
    
}