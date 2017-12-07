package com.kapphk.web.bean.community;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_community_district
 * @author zoneyu 2016-10-31
*/
public class BeanCommunityDistrict extends BaseModel {

    /**
     * 表字段：app_community_district.id 注释：主键id
     * @author zoneyu 2016-10-31
     */
    private String id;
    /**
     * 表字段：app_community_district.community_id 注释：社区id
     * @author zoneyu 2016-10-31
     */
    private Long communityId;
    /**
     * 表字段：app_community_district.province 注释：省
     * @author zoneyu 2016-10-31
     */
    private String province;
    /**
     * 表字段：app_community_district.city 注释：市
     * @author zoneyu 2016-10-31
     */
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}