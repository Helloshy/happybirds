package com.airparking.apms.api.system.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/12.
 */
public class System extends AbstractEntity implements Serializable {
    private Long id;
    private String name;
    private String ip;
    private Long parkId;
    private Byte status;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public enum SystemStatus{
        NORMAL(0),
        ABNORMAL(1);

        private int value;

        SystemStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
