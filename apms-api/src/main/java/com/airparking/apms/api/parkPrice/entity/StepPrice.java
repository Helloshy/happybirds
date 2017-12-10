package com.airparking.apms.api.parkPrice.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Long;

/**
 * Created by ryan on 2016-08-04.
 */
public class StepPrice extends AbstractEntity implements Serializable {
    private Long id;
    private Double parkTime;
    private Integer fee;
    private Long priceId;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setParkTime(Double parkTime){
        this.parkTime = parkTime;
    }

    public Double getParkTime(){
        return this.parkTime;
    }

    public void setFee(Integer fee){
        this.fee = fee;
    }

    public Integer getFee(){
        return this.fee;
    }

    public void setPriceId(Long priceId){
        this.priceId = priceId;
    }

    public Long getPriceId(){
        return this.priceId;
    }

}