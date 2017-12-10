package com.airparking.apms.api.park.entity;

import java.io.Serializable;

import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-08-30.
 */
public class ScanpayPrice extends AbstractEntity implements Serializable {
  private Long id;
  private Long parkId;
  private Long priceId;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setParkId(Long parkId) {
    this.parkId = parkId;
  }

  public Long getParkId() {
    return this.parkId;
  }

  public void setPriceId(Long priceId) {
    this.priceId = priceId;
  }

  public Long getPriceId() {
    return this.priceId;
  }

}