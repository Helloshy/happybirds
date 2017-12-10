package com.airparking.apms.api.record.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * Created by ryan on 2016-08-04.
 */
public class Record extends AbstractEntity implements Serializable {
    private Long id;
    private Long parkId;
    private String name;
    private String username;
    private Date startTime;
    private Date endTime;
    private Integer monthIn;
    private Integer monthOut;
    private Integer apIn;
    private Integer apOut;
    private Integer tempIn;
    private Integer tempOut;
    private Integer currentMonth;
    private Integer currentTemp;
    private Integer currentAp;
    private Integer monthIncome;
    private Integer monthRefund;
    private Integer tempIncome;
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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
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

    public void setMonthIn(Integer monthIn){
        this.monthIn = monthIn;
    }

    public Integer getMonthIn(){
        return this.monthIn;
    }

    public void setMonthOut(Integer monthOut){
        this.monthOut = monthOut;
    }

    public Integer getMonthOut(){
        return this.monthOut;
    }

    public void setApIn(Integer apIn){
        this.apIn = apIn;
    }

    public Integer getApIn(){
        return this.apIn;
    }

    public void setApOut(Integer apOut){
        this.apOut = apOut;
    }

    public Integer getApOut(){
        return this.apOut;
    }

    public void setTempIn(Integer tempIn){
        this.tempIn = tempIn;
    }

    public Integer getTempIn(){
        return this.tempIn;
    }

    public void setTempOut(Integer tempOut){
        this.tempOut = tempOut;
    }

    public Integer getTempOut(){
        return this.tempOut;
    }

    public void setCurrentMonth(Integer currentMonth){
        this.currentMonth = currentMonth;
    }

    public Integer getCurrentMonth(){
        return this.currentMonth;
    }

    public void setCurrentTemp(Integer currentTemp){
        this.currentTemp = currentTemp;
    }

    public Integer getCurrentTemp(){
        return this.currentTemp;
    }

    public void setCurrentAp(Integer currentAp){
        this.currentAp = currentAp;
    }

    public Integer getCurrentAp(){
        return this.currentAp;
    }

    public void setMonthIncome(Integer monthIncome){
        this.monthIncome = monthIncome;
    }

    public Integer getMonthIncome(){
        return this.monthIncome;
    }

    public void setMonthRefund(Integer monthRefund){
        this.monthRefund = monthRefund;
    }

    public Integer getMonthRefund(){
        return this.monthRefund;
    }

    public void setTempIncome(Integer tempIncome){
        this.tempIncome = tempIncome;
    }

    public Integer getTempIncome(){
        return this.tempIncome;
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

}