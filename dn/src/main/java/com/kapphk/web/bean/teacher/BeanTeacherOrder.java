package com.kapphk.web.bean.teacher;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_teacher_order
 * @author zoneyu 2016-10-08
*/
public class BeanTeacherOrder extends BaseModel {

    /**
     * 表字段：app_teacher_order.id 注释：主键id
     * @author zoneyu 2016-10-08
     */
    private Long id;
    /**
     * 表字段：app_teacher_order.teacher_id 注释：名师id
     * @author zoneyu 2016-10-08
     */
    private Long teacherId;
    /**
     * 表字段：app_teacher_order.uid 注释：用户id
     * @author zoneyu 2016-10-08
     */
    private Long uid;
    /**
     * 表字段：app_teacher_order.nick_name 注释：申请人姓名
     * @author zoneyu 2016-10-08
     */
    private String nickName;
    /**
     * 表字段：app_teacher_order.phone 注释：联系电话
     * @author zoneyu 2016-10-08
     */
    private String phone;
    /**
     * 表字段：app_teacher_order.address 注释：讲学地点
     * @author zoneyu 2016-10-08
     */
    private String address;
    /**
     * 表字段：app_teacher_order.company_name 注释：单位名称
     * @author zoneyu 2016-10-08
     */
    private String companyName;
    /**
     * 表字段：app_teacher_order.company_remark 注释：单位简介
     * @author zoneyu 2016-10-08
     */
    private String companyRemark;
    /**
     * 表字段：app_teacher_order.teach_theme 注释：讲学主题
     * @author zoneyu 2016-10-08
     */
    private String teachTheme;
    /**
     * 表字段：app_teacher_order.teach_start 注释：讲学时间开始
     * @author zoneyu 2016-10-08
     */
    private Date teachStart;
    /**
     * 表字段：app_teacher_order.teach_end 注释：讲学时间结束
     * @author zoneyu 2016-10-08
     */
    private Date teachEnd;
    /**
     * 表字段：app_teacher_order.teach_times 注释：讲学次数
     * @author zoneyu 2016-10-08
     */
    private Integer teachTimes;
    /**
     * 表字段：app_teacher_order.remark 注释：备注
     * @author zoneyu 2016-10-08
     */
    private String remark;
    /**
     * 表字段：app_teacher_order.order_amount 注释：费用
     * @author zoneyu 2016-10-08
     */
    private BigDecimal orderAmount;
    /**
     * 表字段：app_teacher_order.discount_amount 注释：抵扣费用
     * @author zoneyu 2016-10-08
     */
    private BigDecimal discountAmount;
    /**
     * 表字段：app_teacher_order.discount_remark 注释：抵扣备注
     * @author zoneyu 2016-10-08
     */
    private String discountRemark;
    /**
     * 表字段：app_teacher_order.actual_amount 注释：实收费用
     * @author zoneyu 2016-10-08
     */
    private BigDecimal actualAmount;
    /**
     * 表字段：app_teacher_order.pay_date 注释：收款时间
     * @author zoneyu 2016-10-08
     */
    private Date payDate;
    /**
     * 表字段：app_teacher_order.record_type 注释：类型 1:动能名师 2:陪伴师
     * @author zoneyu 2016-10-08
     */
    private Integer recordType;
    /**
     * 表字段：app_course_order.discount_blue 注释：蓝币抵扣
     * @author zoneyu 2016-10-24
     */
    private BigDecimal discountBlue;
    /**
     * 表字段：app_course_order.discount_red 注释：红币抵扣
     * @author zoneyu 2016-10-24
     */
    private BigDecimal discountRed;
    
    /**
     * 表字段：app_teacher_order.free_times 注释：赠送场次
     * @author zoneyu 2016-10-08
     */
    private Integer freeTimes;
    /**
     * 表字段：app_teacher_order.personnel 注释：陪同人员
     * @author zoneyu 2016-10-08
     */
    private String personnel;
    /**
     * 表字段：app_course_order.transportation 注释：交通费(0:我方付，1：对方付)
     * @author zoneyu 2016-10-24
     */
    private Integer transportation;
    /**
     * 表字段：app_course_order.reason 注释：赠送原因
     * @author zoneyu 2016-10-24
     */
    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyRemark() {
        return companyRemark;
    }

    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark;
    }

    public String getTeachTheme() {
        return teachTheme;
    }

    public void setTeachTheme(String teachTheme) {
        this.teachTheme = teachTheme;
    }
    @JSONField(format="yyyy-MM-dd")
    public Date getTeachStart() {
        return teachStart;
    }

    public void setTeachStart(Date teachStart) {
        this.teachStart = teachStart;
    }
    @JSONField(format="yyyy-MM-dd")
    public Date getTeachEnd() {
        return teachEnd;
    }

    public void setTeachEnd(Date teachEnd) {
        this.teachEnd = teachEnd;
    }

    public Integer getTeachTimes() {
        return teachTimes;
    }

    public void setTeachTimes(Integer teachTimes) {
        this.teachTimes = teachTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountRemark() {
        return discountRemark;
    }

    public void setDiscountRemark(String discountRemark) {
        this.discountRemark = discountRemark;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
    @JSONField(format="yyyy-MM-dd")
    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

	public BigDecimal getDiscountBlue() {
		return discountBlue;
	}

	public void setDiscountBlue(BigDecimal discountBlue) {
		this.discountBlue = discountBlue;
	}

	public BigDecimal getDiscountRed() {
		return discountRed;
	}

	public void setDiscountRed(BigDecimal discountRed) {
		this.discountRed = discountRed;
	}

	public Integer getFreeTimes() {
		return freeTimes;
	}

	public void setFreeTimes(Integer freeTimes) {
		this.freeTimes = freeTimes;
	}

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	public Integer getTransportation() {
		return transportation;
	}

	public void setTransportation(Integer transportation) {
		this.transportation = transportation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
    
}