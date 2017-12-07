package com.kapphk.web.bean.course;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_type
 * @author zoneyu 2016-10-13
*/
public class BeanCourseType extends BaseModel {

    /**
     * 表字段：app_course_type.id 注释：主键id
     * @author zoneyu 2016-10-13
     */
    private Long id;
    /**
     * 表字段：app_course_type.item_name 注释：课程系列名称
     * @author zoneyu 2016-10-13
     */
    private String itemName;
    /**
     * 表字段：app_course_type.course_tag 注释：课程标签
     * @author zoneyu 2016-10-13
     */
    private String courseTag;
    /**
     * 表字段：app_course_type.course_tag_type 注释：课程标签类型
     * @author zoneyu 2016-10-13
     */
    private String courseTagType;
    /**
     * 表字段：app_course_type.course_group 注释：课程分类
     * @author zoneyu 2016-10-13
     */
    private String courseGroup;
    /**
     * 表字段：app_course_type.course_group_type 注释：课程分类类型
     * @author zoneyu 2016-10-13
     */
    private String courseGroupType;
    /**
     * 表字段：app_course_type.blue_currency_rate 注释：蓝币最大抵扣比例
     * @author zoneyu 2016-10-13
     */
    private BigDecimal blueCurrencyRate;
    /**
     * 表字段：app_course_type.retrain_amount 注释：复训费用
     * @author zoneyu 2016-10-13
     */
    private BigDecimal retrainAmount;
    /**
     * 表字段：app_course_type.course_month 注释：课程有效期
     * @author zoneyu 2016-10-13
     */
    private String courseMonth;
    /**
     * 表字段：app_course_type.course_month_type 注释：课程有效期类型
     * @author zoneyu 2016-10-13
     */
    private String courseMonthType;

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

    public String getCourseTag() {
        return courseTag;
    }

    public void setCourseTag(String courseTag) {
        this.courseTag = courseTag;
    }

    public String getCourseTagType() {
        return courseTagType;
    }

    public void setCourseTagType(String courseTagType) {
        this.courseTagType = courseTagType;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public String getCourseGroupType() {
        return courseGroupType;
    }

    public void setCourseGroupType(String courseGroupType) {
        this.courseGroupType = courseGroupType;
    }

    public BigDecimal getBlueCurrencyRate() {
        return blueCurrencyRate;
    }

    public void setBlueCurrencyRate(BigDecimal blueCurrencyRate) {
        this.blueCurrencyRate = blueCurrencyRate;
    }

    public BigDecimal getRetrainAmount() {
        return retrainAmount;
    }

    public void setRetrainAmount(BigDecimal retrainAmount) {
        this.retrainAmount = retrainAmount;
    }

    public String getCourseMonth() {
        return courseMonth;
    }

    public void setCourseMonth(String courseMonth) {
        this.courseMonth = courseMonth;
    }

    public String getCourseMonthType() {
        return courseMonthType;
    }

    public void setCourseMonthType(String courseMonthType) {
        this.courseMonthType = courseMonthType;
    }
}