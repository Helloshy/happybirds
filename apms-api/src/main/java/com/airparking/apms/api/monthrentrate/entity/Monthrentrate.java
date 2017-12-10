package com.airparking.apms.api.monthrentrate.entity;

import java.io.Serializable;
import java.util.Date;

import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-07-28.
 */
public class Monthrentrate extends AbstractEntity implements Serializable {
  private Long id;
  // 线上月报流水号 根据 mrrid+park_id组合而来
  private String serialMrrid;
  // 线下月报流水号
  private String mrrId;
  private Long parkId;
  // 费率名称
  private String name;
  private Date startDate;
  private Date endDate;
  // 月保费,分是单位
  private Integer monthfee;
  // 管理费,分是单位
  private Integer adminfee;
  private Byte disabled;
  private Byte status;
  private Long version;
  private Byte updateStatus;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setSerialMrrid(String serialMrrid) {
    this.serialMrrid = serialMrrid;
  }

  public String getSerialMrrid() {
    return this.serialMrrid;
  }

  public void setMrrId(String mrrId) {
    this.mrrId = mrrId;
  }

  public String getMrrId() {
    return this.mrrId;
  }

  public void setParkId(Long parkId) {
    this.parkId = parkId;
  }

  public Long getParkId() {
    return this.parkId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getEndDate() {
    return this.endDate;
  }

  public void setMonthfee(Integer monthfee) {
    this.monthfee = monthfee;
  }

  public Integer getMonthfee() {
    return this.monthfee;
  }

  public void setAdminfee(Integer adminfee) {
    this.adminfee = adminfee;
  }

  public Integer getAdminfee() {
    return this.adminfee;
  }

  public void setDisabled(Byte disabled) {
    this.disabled = disabled;
  }

  public Byte getDisabled() {
    return this.disabled;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Byte getStatus() {
    return this.status;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getVersion() {
    return this.version;
  }

	public Byte getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(Byte updateStatus) {
		this.updateStatus = updateStatus;
	}
}