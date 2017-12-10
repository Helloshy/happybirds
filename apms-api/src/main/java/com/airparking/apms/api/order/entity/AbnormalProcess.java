package com.airparking.apms.api.order.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * Created by ryan on 2016-07-27.
 */
public class AbnormalProcess extends AbstractEntity implements Serializable {
    private Long id;
    private Long parkId;
    private Integer type;
    private String plateNo;
    private String orderTradeNo;
    private String reason;
    private Boolean isDeleted;
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

    public void setParkId(Long parkId){
        this.parkId = parkId;
    }

    public Long getParkId(){
        return this.parkId;
    }

    public void setType(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return this.type;
    }

    public void setPlateNo(String plateNo){
        this.plateNo = plateNo;
    }

    public String getPlateNo(){
        return this.plateNo;
    }

    public void setOrderTradeNo(String orderTradeNo){
        this.orderTradeNo = orderTradeNo;
    }

    public String getOrderTradeNo(){
        return this.orderTradeNo;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public String getReason(){
        return this.reason;
    }

    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }

    public Boolean getIsDeleted(){
        return this.isDeleted;
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

    public enum ProcessType {
        MANUAL_OPEN_ENTRY(1),
        MANUAL_OPEN_LEAVE(2),
        DISCARD_ORDER(4),
        MANUAL_END_ORDER(8);

        private int value;

        ProcessType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}