package com.kapphk.yyt.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.base.bean.BaseModel;

/**
 * 用户燃气编号：user_number
 * @author zoneyu 2016-12-06
*/
public class UserNumber extends BaseModel {

    /**
     * 表字段：user_number.id 注释：用户燃气编号
     * @author zoneyu 2016-12-06
     */
    private Long id;
    /**
     * 表字段：user_number.company_id 注释：公司id
     * @author zoneyu 2016-12-06
     */
    private Long companyId;
    /**
     * 表字段：user_number.record_tag 注释：用户类型标签
     * @author zoneyu 2016-12-06
     */
    private String recordTag;
    /**
     * 表字段：user_number.record_tag_type 注释：标签类型 用户类型
     * @author zoneyu 2016-12-06
     */
    private String recordTagType;
    /**
     * 表字段：user_number.real_name 注释：用户姓名
     * @author zoneyu 2016-12-06
     */
    private String realName;
    /**
     * 表字段：user_number.tel 注释：手机号码
     * @author zoneyu 2016-12-06
     */
    private String tel;
    /**
     * 表字段：user_number.province 注释：省(省级)
     * @author zoneyu 2016-12-06
     */
    private String province;
    /**
     * 表字段：user_number.city 注释：市(市级)
     * @author zoneyu 2016-12-06
     */
    private String city;
    /**
     * 表字段：user_number.district 注释：区(县级)
     * @author zoneyu 2016-12-06
     */
    private String district;
    /**
     * 表字段：user_number.community_id 注释：小区ID
     * @author zoneyu 2016-12-06
     */
    private Long communityId;
    /**
     * 表字段：user_number.unit 注释：楼栋/单元
     * @author zoneyu 2016-12-06
     */
    private String unit;
    /**
     * 表字段：user_number.state 注释：气表状态 0:闭栓 1:开栓
     * @author zoneyu 2016-12-06
     */
    private Integer state;
    /**
     * 表字段：user_number.meter_type 注释：气表类型 1:机械表 2:IC卡表
     * @author zoneyu 2016-12-06
     */
    private Integer meterType;
    /**
     * 表字段：user_number.meter_no 注释：气表编号
     * @author zoneyu 2016-12-06
     */
    private Long meterNo;
    /**
     * 表字段：user_number.balance_amount 注释：余额
     * @author zoneyu 2016-12-06
     */
    private BigDecimal balanceAmount;
    /**
     * 表字段：user_number.update_by 注释：操作人
     * @author zoneyu 2016-12-06
     */
    private Long updateBy;
    /**
     * 表字段：user_number.update_time 注释：更新时间
     * @author zoneyu 2016-12-06
     */
    private Date updateTime;

    private Integer status;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getMeterType() {
        return meterType;
    }

    public void setMeterType(Integer meterType) {
        this.meterType = meterType;
    }

    public Long getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(Long meterNo) {
        this.meterNo = meterNo;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}