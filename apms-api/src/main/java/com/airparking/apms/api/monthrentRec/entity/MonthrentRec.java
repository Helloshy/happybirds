package com.airparking.apms.api.monthrentRec.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * Created by ryan on 2016-07-28.
 */
public class MonthrentRec extends AbstractEntity implements Serializable {
    private Long id;
    // 关联monthrent表serial_mrid字段
    private String serialMrid;
    // 关联monthrentrate表serial_mrrid字段
    private String serialMrrid;
    private Long parkId;
    // 主车牌号
    private String mainPlateNo;
    // 车位号
    private String spaceNo;
    private Date startDate;
    private Date endDate;
    // 金额（支付和退款款）
    private Integer amount;
    // 支付方式:0线下;1线上
    private Byte paymentType;
    // 操作类型:开通：0 充值：1 停用：2
    private Byte operationType;
    // 状态，是否生效
    private Byte status;
    private Byte isDeleted;
    // 是否禁用，通常用于覆盖
    private Byte disabled;
    // 车主姓名
    private String ownerName;
    // 车主电话
    private String ownerMobile;
    private String carmode;
    private String remark;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setSerialMrid(String serialMrid){
        this.serialMrid = serialMrid;
    }

    public String getSerialMrid(){
        return this.serialMrid;
    }

    public void setSerialMrrid(String serialMrrid){
        this.serialMrrid = serialMrrid;
    }

    public String getSerialMrrid(){
        return this.serialMrrid;
    }

    public void setParkId(Long parkId){
        this.parkId = parkId;
    }

    public Long getParkId(){
        return this.parkId;
    }

    public void setMainPlateNo(String mainPlateNo){
        this.mainPlateNo = mainPlateNo;
    }

    public String getMainPlateNo(){
        return this.mainPlateNo;
    }

    public void setSpaceNo(String spaceNo){
        this.spaceNo = spaceNo;
    }

    public String getSpaceNo(){
        return this.spaceNo;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public Date getEndDate(){
        return this.endDate;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }

    public Integer getAmount(){
        return this.amount;
    }

    public void setPaymentType(Byte paymentType){
        this.paymentType = paymentType;
    }

    public Byte getPaymentType(){
        return this.paymentType;
    }

    public void setOperationType(Byte operationType){
        this.operationType = operationType;
    }

    public Byte getOperationType(){
        return this.operationType;
    }

    public void setStatus(Byte status){
        this.status = status;
    }

    public Byte getStatus(){
        return this.status;
    }

    public void setIsDeleted(Byte isDeleted){
        this.isDeleted = isDeleted;
    }

    public Byte getIsDeleted(){
        return this.isDeleted;
    }

    public void setDisabled(Byte disabled){
        this.disabled = disabled;
    }

    public Byte getDisabled(){
        return this.disabled;
    }

    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }

    public String getOwnerName(){
        return this.ownerName;
    }

    public void setOwnerMobile(String ownerMobile){
        this.ownerMobile = ownerMobile;
    }

    public String getOwnerMobile(){
        return this.ownerMobile;
    }

    public void setCarmode(String carmode){
        this.carmode = carmode;
    }

    public String getCarmode(){
        return this.carmode;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }

    public Date getCreatedDate(){
        return this.createdDate;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public String getCreatedBy(){
        return this.createdBy;
    }

    public void setUpdatedDate(Date updatedDate){
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate(){
        return this.updatedDate;
    }

    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy(){
        return this.updatedBy;
    }

}