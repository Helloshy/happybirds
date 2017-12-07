package com.kapphk.web.bean.community;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_community
 * @author zoneyu 2016-11-04
*/
public class BeanCommunity extends BaseModel {

    /**
     * 表字段：app_community.id 注释：主键id
     * @author zoneyu 2016-11-04
     */
    private Long id;
    /**
     * 表字段：app_community.record_type 注释：小组类别 1:个人社区 2:官方社区
     * @author zoneyu 2016-11-04
     */
    private Integer recordType;
    /**
     * 表字段：app_community.logo_path 注释：小组图片
     * @author zoneyu 2016-11-04
     */
    private String logoPath;
    /**
     * 表字段：app_community.item_name 注释：小组名称
     * @author zoneyu 2016-11-04
     */
    private String itemName;
    /**
     * 表字段：app_community.uid 注释：区长 用户id
     * @author zoneyu 2016-11-04
     */
    private Long uid;
    /**
     * 表字段：app_community.photo_path1 注释：社区图片1
     * @author zoneyu 2016-11-04
     */
    private String photoPath1;
    /**
     * 表字段：app_community.photo_path2 注释：社区图片2
     * @author zoneyu 2016-11-04
     */
    private String photoPath2;
    /**
     * 表字段：app_community.photo_path3 注释：社区图片3
     * @author zoneyu 2016-11-04
     */
    private String photoPath3;
    /**
     * 表字段：app_community.remark 注释：社区介绍
     * @author zoneyu 2016-11-04
     */
    private String remark;
    
    private String userName ;

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhotoPath1() {
        return photoPath1;
    }

    public void setPhotoPath1(String photoPath1) {
        this.photoPath1 = photoPath1;
    }

    public String getPhotoPath2() {
        return photoPath2;
    }

    public void setPhotoPath2(String photoPath2) {
        this.photoPath2 = photoPath2;
    }

    public String getPhotoPath3() {
        return photoPath3;
    }

    public void setPhotoPath3(String photoPath3) {
        this.photoPath3 = photoPath3;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}