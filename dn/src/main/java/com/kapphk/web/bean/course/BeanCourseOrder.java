package com.kapphk.web.bean.course;

import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：app_course_order
 * @author zoneyu 2016-11-17
*/
public class BeanCourseOrder extends BaseModel {

    /**
     * 表字段：app_course_order.id 注释：主键id
     * @author zoneyu 2016-11-17
     */
    private Long id;
    /**
     * 表字段：app_course_order.order_no 注释：订单号
     * @author zoneyu 2016-11-17
     */
    private String orderNo;
    /**
     * 表字段：app_course_order.course_id 注释：课程id
     * @author zoneyu 2016-11-17
     */
    private Long courseId;
    /**
     * 表字段：app_course_order.course_type_id 注释：课程系列id
     * @author zoneyu 2016-11-17
     */
    private Long courseTypeId;
    /**
     * 表字段：app_course_order.record_type 注释：1:线下课程 2:网络课程 3:系统课程
     * @author zoneyu 2016-11-17
     */
    private Integer recordType;
    /**
     * 表字段：app_course_order.uid 注释：用户id
     * @author zoneyu 2016-11-17
     */
    private Long uid;
    /**
     * 表字段：app_course_order.goods_id 注释：物品id
     * @author zoneyu 2016-11-17
     */
    private Long goodsId;
    /**
     * 表字段：app_course_order.qty 注释：数量
     * @author zoneyu 2016-11-17
     */
    private Integer qty;
    /**
     * 表字段：app_course_order.origin_amount 注释：课程费用
     * @author zoneyu 2016-11-17
     */
    private BigDecimal originAmount;
    /**
     * 表字段：app_course_order.coupon_id 注释：抵扣券id
     * @author zoneyu 2016-11-17
     */
    private Long couponId;
    /**
     * 表字段：app_course_order.is_coupon 注释：是否消耗(使用抵扣券时为1) 0:否 1:是
     * @author zoneyu 2016-11-17
     */
    private Integer isCoupon;
    /**
     * 表字段：app_course_order.discount_blue 注释：蓝币抵扣
     * @author zoneyu 2016-11-17
     */
    private BigDecimal discountBlue;
    /**
     * 表字段：app_course_order.discount_red 注释：红币抵扣
     * @author zoneyu 2016-11-17
     */
    private BigDecimal discountRed;
    /**
     * 表字段：app_course_order.pay_amount 注释：实收交易金额
     * @author zoneyu 2016-11-17
     */
    private BigDecimal payAmount;
    /**
     * 表字段：app_course_order.pay_method 注释：支付方式
     * @author zoneyu 2016-11-17
     */
    private String payMethod;
    /**
     * 表字段：app_course_order.pay_method_type 注释：支付方式标签类型
     * @author zoneyu 2016-11-17
     */
    private String payMethodType;
    /**
     * 表字段：app_course_order.pay_status 注释：课程状态 1:新训 2:复训 3:赞赏
     * @author zoneyu 2016-11-17
     */
    private Integer payStatus;
    /**
     * 表字段：app_course_order.is_pay 注释：1已支付；0未支付 2:取消
     * @author zoneyu 2016-11-17
     */
    private Integer isPay;
    /**
     * 表字段：app_course_order.pay_time 注释：支付时间
     * @author zoneyu 2016-11-17
     */
    private Date payTime;
    /**
     * 表字段：app_course_order.course_code 注释：报课码
     * @author zoneyu 2016-11-17
     */
    private String courseCode;
    /**
     * 表字段：app_course_order.is_use 注释：是否使用 0:不使用 1:使用
     * @author zoneyu 2016-11-17
     */
    private Integer isUse;
    /**
     * 表字段：app_course_order.student_name 注释：学员姓名
     * @author zoneyu 2016-11-17
     */
    private String studentName;
    /**
     * 表字段：app_course_order.student_phone 注释：学员手机号
     * @author zoneyu 2016-11-17
     */
    private String studentPhone;
    /**
     * 表字段：app_course_order.validation_uid 注释：验证人用户ID
     * @author zoneyu 2016-11-17
     */
    private Long validationUid;
    /**
     * 表字段：app_course_order.validation_time 注释：验证时间
     * @author zoneyu 2016-11-17
     */
    private Date validationTime;
    /**
     * 表字段：app_course_order.student_id_card 注释：学员身份证号
     * @author zoneyu 2016-11-17
     */
    private String studentIdCard;
    /**
     * 表字段：app_course_order.expiration_date 注释：过期时间
     * @author zoneyu 2016-11-17
     */
    private Date expirationDate;
    /**
     * 表字段：app_course_order.system_course_name 注释：系统课程名称
     * @author zoneyu 2016-11-17
     */
    private String systemCourseName;
    /**
     * 表字段：app_course_order.address 注释：收货地址
     * @author zoneyu 2016-11-17
     */
    private String address;

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getOriginAmount() {
        return originAmount;
    }

    public void setOriginAmount(BigDecimal originAmount) {
        this.originAmount = originAmount;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Integer getIsCoupon() {
        return isCoupon;
    }

    public void setIsCoupon(Integer isCoupon) {
        this.isCoupon = isCoupon;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethodType() {
        return payMethodType;
    }

    public void setPayMethodType(String payMethodType) {
        this.payMethodType = payMethodType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public Long getValidationUid() {
        return validationUid;
    }

    public void setValidationUid(Long validationUid) {
        this.validationUid = validationUid;
    }

    public Date getValidationTime() {
        return validationTime;
    }

    public void setValidationTime(Date validationTime) {
        this.validationTime = validationTime;
    }

    public String getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(String studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSystemCourseName() {
        return systemCourseName;
    }

    public void setSystemCourseName(String systemCourseName) {
        this.systemCourseName = systemCourseName;
    }
}