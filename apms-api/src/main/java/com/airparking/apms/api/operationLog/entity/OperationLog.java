package com.airparking.apms.api.operationLog.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * Created by ryan on 2016-08-03.
 */
public class OperationLog extends AbstractEntity implements Serializable {
    private Long id;
    private String module;
    private String type;
    private Long parkId;
    private String operation;
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

    public void setModule(String module){
        this.module = module;
    }

    public String getModule(){
        return this.module;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setParkId(Long parkId){
        this.parkId = parkId;
    }

    public Long getParkId(){
        return this.parkId;
    }

    public void setOperation(String operation){
        this.operation = operation;
    }

    public String getOperation(){
        return this.operation;
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