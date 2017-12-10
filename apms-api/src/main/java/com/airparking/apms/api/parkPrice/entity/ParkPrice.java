package com.airparking.apms.api.parkPrice.entity;

import java.io.Serializable;

import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-08-05.
 */
public class ParkPrice extends AbstractEntity implements Serializable {
  private Long id;
  private String rateId;
  private Long parkId;
  // 限制时长（小时）
  private Integer limitTime;
  // 限制金额
  private Integer limitFee;
  // 1-起收时长不累计在总停车时长中, 2-跨昼夜分界点且时间不足分界前的一个单位时长时，按分界点前的计费单位进行计费
  private Byte flags;
  // 关联car_model表
  private Long carModelId;
  // 1-计时收费， 2-阶梯收费
  private Byte priceType;
  private boolean isDisabled;
  private boolean isSend=false;
  private Integer retentionTime;

  public Integer getRetentionTime() {
    return retentionTime;
  }

  public void setRetentionTime(Integer retentionTime) {
    this.retentionTime = retentionTime;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setRateId(String rateId) {
    this.rateId = rateId;
  }

  public String getRateId() {
    return this.rateId;
  }

  public void setParkId(Long parkId) {
    this.parkId = parkId;
  }

  public Long getParkId() {
    return this.parkId;
  }

  public void setLimitTime(Integer limitTime) {
    this.limitTime = limitTime;
  }

  public Integer getLimitTime() {
    return this.limitTime;
  }

  public void setLimitFee(Integer limitFee) {
    this.limitFee = limitFee;
  }

  public Integer getLimitFee() {
    return this.limitFee;
  }

  public void setFlags(Byte flags) {
    this.flags = flags;
  }

  public Byte getFlags() {
    return this.flags;
  }

  public void setCarModelId(Long carModelId) {
    this.carModelId = carModelId;
  }

  public Long getCarModelId() {
    return this.carModelId;
  }

  public void setPriceType(Byte priceType) {
    this.priceType = priceType;
  }

  public Byte getPriceType() {
    return this.priceType;
  }

  public boolean isDisabled() {
    return isDisabled;
  }

  public void setDisabled(boolean isDisabled) {
    this.isDisabled = isDisabled;
  }

  public boolean isSend() {
    return isSend;
  }

  public void setSend(boolean isSend) {
    this.isSend = isSend;
  }
}