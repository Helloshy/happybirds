package com.kapphk.yyt.bean;

import java.util.Date;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键：app_company_service
 * @author zoneyu 2016-12-06
*/
public class AppCompanyService extends BaseModel {

    /**
     * 表字段：app_company_service.id 注释：主键
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：app_company_service.company_id 注释：商家ID
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    
    private String itemName;
    /**
     * 表字段：app_company_service.logo_path 注释：图片路径
     * @author zoneyu 2016-12-06
     */
    private String logoPath;
    
    /**
     * 表字段：app_company_service.tel 注释：联系电话
     * @author zoneyu 2016-12-06
     */
    private String tel;
    /**
     * 表字段：app_company_service.record_type 注释：类型 1:报装 2:报修 3:安检
     * @author zoneyu 2016-12-06
     */
    private Integer recordType;
    /**
     * 表字段：app_company_service.update_by 注释：操作人
     * @author zoneyu 2016-12-06
     */
    private Long updateBy;
    /**
     * 表字段：app_company_service.update_time 注释：更新时间
     * @author zoneyu 2016-12-06
     */
    private Date updateTime;
    /**
     * 表字段：app_company_service.content 注释：服务内容
     * @author zoneyu 2016-12-06
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
    
}