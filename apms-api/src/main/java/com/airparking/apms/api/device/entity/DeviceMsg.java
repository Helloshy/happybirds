package com.airparking.apms.api.device.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @create 2017-07-25-14:13
 */

public class DeviceMsg implements Serializable{
	private Long id;
	private Byte type;
	private Long parkId;
	private String ip;
	private Long deviceId;
	private Boolean flags;
	private Date createdDate;
	private String msg;
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Boolean getFlags() {
		return flags;
	}

	public void setFlags(Boolean flags) {
		this.flags = flags;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

}
