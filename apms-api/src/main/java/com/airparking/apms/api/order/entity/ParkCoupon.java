package com.airparking.apms.api.order.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * Created by ryan on 2016-09-02.
 */
public class ParkCoupon extends AbstractEntity implements Serializable {
    private Long id;
    private Long parkId;
    private String plateNo;
    private String code;
    // 0:有效 1：无效
    private Byte status;
    // 电话号码
    private String mobile;
    private Long orderId;
    private Byte type;
    private Integer discount;
    private Integer limitTime;
    private Date effectiveDate;
    private Date expiredDate;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setParkId(Long parkId){
        this.parkId = parkId;
    }

    public Long getParkId(){
        return this.parkId;
    }

    public void setPlateNo(String plateNo){
        this.plateNo = plateNo;
    }

    public String getPlateNo(){
        return this.plateNo;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setStatus(Byte status){
        this.status = status;
    }

    public Byte getStatus(){
        return this.status;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getMobile(){
        return this.mobile;
    }

    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }

    public Long getOrderId(){
        return this.orderId;
    }

    public void setType(Byte type){
        this.type = type;
    }

    public Byte getType(){
        return this.type;
    }

    public void setDiscount(Integer discount){
        this.discount = discount;
    }

    public Integer getDiscount(){
        return this.discount;
    }

    public void setLimitTime(Integer limitTime){
        this.limitTime = limitTime;
    }

    public Integer getLimitTime(){
        return this.limitTime;
    }

    public void setEffectiveDate(Date effectiveDate){
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate(){
        return this.effectiveDate;
    }

    public void setExpiredDate(Date expiredDate){
        this.expiredDate = expiredDate;
    }

    public Date getExpiredDate(){
        return this.expiredDate;
    }
}