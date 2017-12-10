package com.airparking.apms.api.bwlist.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * Created by ryan on 2016-07-26.
 */
public class ParkBWList extends AbstractEntity implements Serializable {
    private Long id;
    private Long parkId;
    private String plateNo;
    private String mobile;
    private Date effectiveDate;
    private Date expiredDate;
    private Integer flags;
    private Integer type;
    private String reason;

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

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getMobile(){
        return this.mobile;
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

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public String getReason(){
        return this.reason;
    }

    public enum BWListFlags {
        WHITE(1), BLACK(2);

        private int value;

        BWListFlags(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}