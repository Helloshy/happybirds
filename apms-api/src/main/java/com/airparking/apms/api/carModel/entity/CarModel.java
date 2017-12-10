package com.airparking.apms.api.carModel.entity;

import java.io.Serializable;

import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-08-05.
 */
public class CarModel extends AbstractEntity implements Serializable {
    private Long id;
    private String name;
    private Byte type;
    private Long parkId;
    private String code;
    private Boolean isDisabled;
    private Boolean isSend;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public Boolean getIsSend() {
      return isSend;
    }

    public void setIsSend(Boolean isSend) {
      this.isSend = isSend;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setType(Byte type){
        this.type = type;
    }

    public Byte getType(){
        return this.type;
    }

    public void setParkId(Long parkId){
        this.parkId = parkId;
    }

    public Long getParkId(){
        return this.parkId;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setIsDisabled(Boolean isDisabled){
        this.isDisabled = isDisabled;
    }

    public Boolean getIsDisabled(){
        return this.isDisabled;
    }
}