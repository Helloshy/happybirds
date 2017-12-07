package com.kapphk.web.bean.community;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_community
 * @author zoneyu 2016-10-31
*/
public class BeanUserCommunity extends BaseModel {

    /**
     * 表字段：user_community.id 注释：主键id
     * @author zoneyu 2016-10-31
     */
    private Long id;
    /**
     * 表字段：user_community.uid 注释：用户id
     * @author zoneyu 2016-10-31
     */
    private Long uid;
    /**
     * 表字段：user_community.community_id 注释：社区id
     * @author zoneyu 2016-10-31
     */
    private Long communityId;
    private Integer recordType;

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
    
}