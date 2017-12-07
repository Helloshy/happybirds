package com.kapphk.web.bean.course;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course
 * @author zoneyu 2016-12-22
*/
public class BeanCourse extends BaseModel {

    /**
     * 表字段：app_course.id 注释：主键id
     * @author zoneyu 2016-12-22
     */
    private Long id;
    /**
     * 表字段：app_course.item_name 注释：课程标题
     * @author zoneyu 2016-12-22
     */
    private String itemName;
    /**
     * 表字段：app_course.logo_path 注释：课程封面
     * @author zoneyu 2016-12-22
     */
    private String logoPath;
    /**
     * 表字段：app_course.amount 注释：报名费用
     * @author zoneyu 2016-12-22
     */
    private BigDecimal amount;
    /**
     * 表字段：app_course.is_public 注释：公益课 0:否 1:是
     * @author zoneyu 2016-12-22
     */
    private Integer isPublic;
    /**
     * 表字段：app_course.record_type 注释：1:线下课程 2:网络课程 3:系统课程
     * @author zoneyu 2016-12-22
     */
    private Integer recordType;
    /**
     * 表字段：app_course.is_financial 注释：是否财商课程(0:否，1：是)
     * @author zoneyu 2016-12-22
     */
    private Integer isFinancial;
    /**
     * 表字段：app_course.is_offline 注释：是否线下支付(0：否，1：是)
     * @author zoneyu 2016-12-22
     */
    private Integer isOffline;
    /**
     * 表字段：app_course.sort 注释：排序
     * @author zoneyu 2016-12-22
     */
    private Integer sort;
    /**
     * 表字段：app_course.state 注释：状态 0:已下线 1:报名中 2:报名结束 3:已开课
     * @author zoneyu 2016-12-22
     */
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getIsFinancial() {
        return isFinancial;
    }

    public void setIsFinancial(Integer isFinancial) {
        this.isFinancial = isFinancial;
    }

    public Integer getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(Integer isOffline) {
        this.isOffline = isOffline;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}