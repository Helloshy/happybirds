package com.airparking.apms.api.device.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;

/**
 * @create 2017-07-25-11:58
 */

public class PcInfo extends AbstractEntity implements Serializable {

	private Long id;
	private Long deviceId;
	private Integer querySpeed;
	private Integer deriveSpeed;
	private Integer cpuUtilization;
	private Integer cpuTemperature;
	private Integer memoryUsage;
	private Integer hardDiskUsage;
	private Long parkId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getQuerySpeed() {
		return querySpeed;
	}

	public void setQuerySpeed(Integer querySpeed) {
		this.querySpeed = querySpeed;
	}

	public Integer getDeriveSpeed() {
		return deriveSpeed;
	}

	public void setDeriveSpeed(Integer deriveSpeed) {
		this.deriveSpeed = deriveSpeed;
	}

	public Integer getCpuUtilization() {
		return cpuUtilization;
	}

	public void setCpuUtilization(Integer cpuUtilization) {
		this.cpuUtilization = cpuUtilization;
	}

	public Integer getCpuTemperature() {
		return cpuTemperature;
	}

	public void setCpuTemperature(Integer cpuTemperature) {
		this.cpuTemperature = cpuTemperature;
	}

	public Integer getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(Integer memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

	public Integer getHardDiskUsage() {
		return hardDiskUsage;
	}

	public void setHardDiskUsage(Integer hardDiskUsage) {
		this.hardDiskUsage = hardDiskUsage;
	}

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}
}