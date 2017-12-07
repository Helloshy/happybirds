package com.kapphk.web.bean.course;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_coupon
 * @author zoneyu 2016-09-22
*/
public class BeanUserCoupon extends BaseModel {

    /**
     * 表字段：user_coupon.id 注释：主键id
     * @author zoneyu 2016-09-22
     */
    private Long id;
    /**
     * 表字段：user_coupon.uid 注释：用户id
     * @author zoneyu 2016-09-22
     */
    private Long uid;
    /**
     * 表字段：user_coupon.course_id 注释：课程id
     * @author zoneyu 2016-09-22
     */
    private Long courseId;
    /**
     * 表字段：user_coupon.coupon_name 注释：抵扣券名称
     * @author zoneyu 2016-09-22
     */
    private String couponName;
    /**
     * 表字段：user_coupon.coupon_content 注释：抵扣券说明
     * @author zoneyu 2016-09-22
     */
    private String couponContent;
    /**
     * 表字段：user_coupon.date_from 注释：有效期开始
     * @author zoneyu 2016-09-22
     */
    private Date dateFrom;
    /**
     * 表字段：user_coupon.date_to 注释：有效期结束
     * @author zoneyu 2016-09-22
     */
    private Date dateTo;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponContent() {
        return couponContent;
    }

    public void setCouponContent(String couponContent) {
        this.couponContent = couponContent;
    }
    @JSONField(format="yyyy-MM-dd")
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }
    @JSONField(format="yyyy-MM-dd")
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}