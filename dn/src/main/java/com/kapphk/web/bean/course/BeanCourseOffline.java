package com.kapphk.web.bean.course;

import java.math.BigDecimal;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_offline
 * @author zoneyu 2016-10-13
*/
public class BeanCourseOffline extends BaseModel {

    /**
     * 表字段：app_course_offline.id 注释：主键id
     * @author zoneyu 2016-10-13
     */
    private Long id;
    /**
     * 表字段：app_course_offline.course_type_id 注释：课程系列id
     * @author zoneyu 2016-10-13
     */
    private Long courseTypeId;
    /**
     * 表字段：app_course_offline.register_date 注释：报到时间
     * @author zoneyu 2016-10-13
     */
    private String registerDate;
    /**
     * 表字段：app_course_offline.province 注释：省
     * @author zoneyu 2016-10-13
     */
    private String province;
    /**
     * 表字段：app_course_offline.city 注释：市
     * @author zoneyu 2016-10-13
     */
    private String city;
    /**
     * 表字段：app_course_offline.district 注释：区
     * @author zoneyu 2016-10-13
     */
    private String district;
    /**
     * 表字段：app_course_offline.address 注释：详细地址
     * @author zoneyu 2016-10-13
     */
    private String address;
    /**
     * 表字段：app_course_offline.offline_amount 注释：线下成交费用
     * @author zoneyu 2016-10-13
     */
    private BigDecimal offlineAmount;
    /**
     * 表字段：app_course_offline.is_recommend 注释：首页推荐 0:否 1:是
     * @author zoneyu 2016-10-13
     */
    private Integer isRecommend;
    /**
     * 表字段：app_course_offline.course_id 注释：推荐课程1
     * @author zoneyu 2016-10-13
     */
    private Long courseId;
    /**
     * 表字段：app_course_offline.course_id2 注释：推荐课程2
     * @author zoneyu 2016-10-13
     */
    private Long courseId2;
    /**
     * 表字段：app_course_offline.phone 注释：联系电话
     * @author zoneyu 2016-10-13
     */
    private String phone;
    /**
     * 表字段：app_course_offline.course_content 注释：课程介绍
     * @author zoneyu 2016-10-13
     */
    private String courseContent;
    /**
     * 表字段：app_course_offline.course_remark 注释：报到须知
     * @author zoneyu 2016-10-13
     */
    private String courseRemark;
    /**
     * 表字段：app_course_offline.subsidy 注释：教务补贴费
     * @author zoneyu 2016-10-13
     */
    private BigDecimal subsidy;
    /**
     * 表字段：app_course_offline.remark 注释：摘要
     * @author zoneyu 2016-10-13
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getOfflineAmount() {
        return offlineAmount;
    }

    public void setOfflineAmount(BigDecimal offlineAmount) {
        this.offlineAmount = offlineAmount;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId2() {
        return courseId2;
    }

    public void setCourseId2(Long courseId2) {
        this.courseId2 = courseId2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(BigDecimal subsidy) {
        this.subsidy = subsidy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public String getCourseRemark() {
		return courseRemark;
	}

	public void setCourseRemark(String courseRemark) {
		this.courseRemark = courseRemark;
	}
    
}