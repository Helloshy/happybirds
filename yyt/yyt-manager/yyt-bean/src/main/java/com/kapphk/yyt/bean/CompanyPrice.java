package com.kapphk.yyt.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kapphk.base.bean.BaseModel;

/**
 * 主键：app_company_price
 * @author zoneyu 2016-12-06
*/
public class CompanyPrice extends BaseModel {

    /**
     * 表字段：app_company_price.id 注释：主键
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：app_company_price.company_id 注释：商家ID
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    /**
     * 表字段：app_company_price.record_tag 注释：用户类型标签
     * @author zoneyu 2016-12-06
     */
    private String recordTag;
    /**
     * 表字段：app_company_price.record_tag_type 注释：标签类型 用户类型
     * @author zoneyu 2016-12-06
     */
    private String recordTagType;
    /**
     * 表字段：app_company_price.price 注释：气价费用 元/立方米
     * @author zoneyu 2016-12-06
     */
    private BigDecimal price;
    /**
     * 表字段：app_company_price.price_date 注释：气价生效时间
     * @author zoneyu 2016-12-06
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  
    private Date priceDate;
    /**
     * 表字段：app_company_price.update_by 注释：操作人
     * @author zoneyu 2016-12-06
     */
    private Long updateBy;
    /**
     * 表字段：app_company_price.update_time 注释：更新时间
     * @author zoneyu 2016-12-06
     */
    private Date updateTime;

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

    public String getRecordTag() {
        return recordTag;
    }

    public void setRecordTag(String recordTag) {
        this.recordTag = recordTag;
    }

    public String getRecordTagType() {
        return recordTagType;
    }

    public void setRecordTagType(String recordTagType) {
        this.recordTagType = recordTagType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
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
}