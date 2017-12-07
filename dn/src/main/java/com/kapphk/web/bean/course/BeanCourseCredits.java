package com.kapphk.web.bean.course;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键ID：app_course_credits
 * @author zoneyu 2016-11-25
*/
public class BeanCourseCredits extends BaseModel {

    /**
     * 表字段：app_course_credits.id 注释：主键ID
     * @author zoneyu 2016-11-25
     */
    private Long id;
    /**
     * 表字段：app_course_credits.uid 注释：用户ID
     * @author zoneyu 2016-11-25
     */
    private Long uid;
    /**
     * 表字段：app_course_credits.month 注释：月份
     * @author zoneyu 2016-11-25
     */
    private String month;
    /**
     * 表字段：app_course_credits.coupon_id 注释：抵扣卷ID
     * @author zoneyu 2016-11-25
     */
    private String couponId;
    /**
     * 表字段：app_course_credits.course_type_name 注释：课程系列名称
     * @author zoneyu 2016-11-25
     */
    private String courseTypeName;
    /**
     * 表字段：app_course_credits.total_amount 注释：课程抵扣券总数量
     * @author zoneyu 2016-11-25
     */
    private Integer totalAmount;
    /**
     * 表字段：app_course_credits.buy_amount 注释：已购买课程名额
     * @author zoneyu 2016-11-25
     */
    private Integer buyAmount;
    /**
     * 表字段：app_course_credits.use_amount 注释：已上课消耗名额
     * @author zoneyu 2016-11-25
     */
    private Integer useAmount;
    /**
     * 表字段：app_course_credits.last_amount 注释：剩余名额
     * @author zoneyu 2016-11-25
     */
    private Integer lastAmount;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Integer getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }

    public Integer getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(Integer lastAmount) {
        this.lastAmount = lastAmount;
    }
}