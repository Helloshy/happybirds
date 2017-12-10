package com.airparking.apms.api.groupMonrent.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/14.
 */
public class GroupMonrent extends AbstractEntity implements Serializable {
    private Long id;
    private Long parkId;
    private String gmrid;
    private String name;
    private String mobile;
    private Date startTime;
    private Date endTime;
    private Byte status;
    private Integer spaceNum;
    private Integer carNum;
    private String plateNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    public String getGmrid() {
        return gmrid;
    }

    public void setGmrid(String gmrid) {
        this.gmrid = gmrid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSpaceNum() {
        return spaceNum;
    }

    public void setSpaceNum(Integer spaceNum) {
        this.spaceNum = spaceNum;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
}
