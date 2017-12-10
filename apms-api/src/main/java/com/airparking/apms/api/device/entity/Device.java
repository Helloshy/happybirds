package com.airparking.apms.api.device.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/11.
 */
public class Device extends AbstractEntity implements Serializable {

    private Long id;
    private String name;
    private String ip;
    private Long laneId;
    private Byte status;
    private Byte type;
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

    public Long getLaneId() {
        return laneId;
    }

    public void setLaneId(Long laneId) {
        this.laneId = laneId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public enum DeviceStatus{
        NORMAL(0),
        ABNORMAL(1);
        private int value;

        DeviceStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum DeviceType{
        CAMERA(0),//摄像枪
        GATE(1),//闸机
		SYSTEM(2),//系统
		ETC(3),//ETC
		Agent(4);
        private int value;

        DeviceType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
