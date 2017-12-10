package com.airparking.apms.api.lane.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/12.
 */
public class Lane extends AbstractEntity implements Serializable {
    private Long id;
    private String name;
    private String ip;
    private Long parkId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }
}
