package com.airparking.apms.api.parkPrice.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.util.Date;

/**
 * Created by ryan on 2016-07-28.
 */
public class ParkPriceExt extends AbstractEntity implements Serializable {
    private Long id;
    private Integer freeTime;
    private Integer extFreeTime;
    // 分钟
    private Integer priceUnit;
    // 分
    private Integer price;
    private Integer limitTime;
    private Integer limitFee;
    private Date startTime;
    private Date endTime;
    private Integer maxFee;
    private Long priceId;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setFreeTime(Integer freeTime){
        this.freeTime = freeTime;
    }

    public Integer getFreeTime(){
        return this.freeTime;
    }

    public void setExtFreeTime(Integer extFreeTime){
        this.extFreeTime = extFreeTime;
    }

    public Integer getExtFreeTime(){
        return this.extFreeTime;
    }

    public void setPriceUnit(Integer priceUnit){
        this.priceUnit = priceUnit;
    }

    public Integer getPriceUnit(){
        return this.priceUnit;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public Integer getPrice(){
        return this.price;
    }

    public void setLimitTime(Integer limitTime){
        this.limitTime = limitTime;
    }

    public Integer getLimitTime(){
        return this.limitTime;
    }

    public void setLimitFee(Integer limitFee){
        this.limitFee = limitFee;
    }

    public Integer getLimitFee(){
        return this.limitFee;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }

    public Date getStartTime(){
        return this.startTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }

    public Date getEndTime(){
        return this.endTime;
    }

    public void setMaxFee(Integer maxFee){
        this.maxFee = maxFee;
    }

    public Integer getMaxFee(){
        return this.maxFee;
    }

    public void setPriceId(Long priceId){
        this.priceId = priceId;
    }

    public Long getPriceId(){
        return this.priceId;
    }

}