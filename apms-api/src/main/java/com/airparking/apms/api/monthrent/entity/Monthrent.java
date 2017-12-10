package com.airparking.apms.api.monthrent.entity;

import java.io.Serializable;
import java.util.Date;

import com.airparking.apms.api.monthrentrate.entity.Monthrentrate;
import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-07-28.
 */
public class Monthrent extends AbstractEntity implements Serializable {
    private Long id;
    // 关联rate表serial_mrrid字段
    private String serialMrrId;
    // 月报流水号 mrid和park_id组合而来
    private String serialMrid;
    // 线下月报流水号
    private String mrid;
    private Long parkId;
    // 主车牌号
    private String mainPlateNo;
    // 车位号
    private String spaceNo;
    private Date startDate;
    private Date endDate;
    private Byte status;
    // 是否禁用，通常用于覆盖
    private Byte disabled;
    // 车主姓名
    private String ownerName;
    // 车主电话
    private String ownerMobile;
    private String carmode;
    private String remark;
    private Long version;
    private Monthrentrate monthrentrate;
    private String cardNo;
    private String otherPlateNo;
    private Integer amount;

    public String getCardNo() {
      return cardNo;
    }

    public void setCardNo(String cardNo) {
      this.cardNo = cardNo;
    }

    public String getOtherPlateNo() {
      return otherPlateNo;
    }

    public void setOtherPlateNo(String otherPlateNo) {
      this.otherPlateNo = otherPlateNo;
    }

    public Integer getAmount() {
      return amount;
    }

    public void setAmount(Integer amount) {
      this.amount = amount;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public Monthrentrate getMonthrentrate() {
      return monthrentrate;
    }

    public void setMonthrentrate(Monthrentrate monthrentrate) {
      this.monthrentrate = monthrentrate;
    }

    public void setSerialMrrId(String serialMrrId){
        this.serialMrrId = serialMrrId;
    }

    public String getSerialMrrId(){
        return this.serialMrrId;
    }

    public void setSerialMrid(String serialMrid){
        this.serialMrid = serialMrid;
    }

    public String getSerialMrid(){
        return this.serialMrid;
    }

    public void setMrid(String mrid){
        this.mrid = mrid;
    }

    public String getMrid(){
        return this.mrid;
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

    public void setStatus(Byte status){
        this.status = status;
    }

    public Byte getStatus(){
        return this.status;
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

    public void setVersion(Long version){
        this.version = version;
    }

    public Long getVersion(){
        return this.version;
    }

}