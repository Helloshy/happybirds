package com.airparking.apms.api.payment.entity;

import java.io.Serializable;

import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-07-27.
 */
public class Payment extends AbstractEntity implements Serializable {
    private Long id;
    private Long parkId;
    private String tradeNo;
    private String plateNo;
    // 1-临停， 2-月保， 4-online
    private Byte type;
    private Integer amount;
    private String outTradeNo;
    // 1-现金
    private Integer paymentWay;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setParkId(Long parkId){
        this.parkId = parkId;
    }

    public Long getParkId(){
        return this.parkId;
    }

    public void setTradeNo(String tradeNo){
        this.tradeNo = tradeNo;
    }

    public String getTradeNo(){
        return this.tradeNo;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public void setType(Byte type){
        this.type = type;
    }

    public Byte getType(){
        return this.type;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }

    public Integer getAmount(){
        return this.amount;
    }

    public void setOutTradeNo(String outTradeNo){
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo(){
        return this.outTradeNo;
    }

    public void setPaymentWay(Integer paymentWay){
        this.paymentWay = paymentWay;
    }

    public Integer getPaymentWay(){
        return this.paymentWay;
    }
    
    public enum PaymentWay{
      CASH(0,"现金"),
      QRCODE(1,"扫码支付"),
      POSTCHARGE(2,"缴费机收费"),
      ETC(3,"ETC收费"),
      ONLINECHARGE(4,"线上收费"),
      PREPAID(5,"预付扣费"),
      AFTERPAID(6,"后付扣费"),
      OTHER(7,"其他"),
	  TRANSFER(8,"银行转账");
      
      private int value;
      private String desc;
      
      PaymentWay(int value,String desc) {
        this.value = value;
        this.desc = desc;
      }

      public int getValue() {
        return value;
      }

      public void setValue(int value) {
        this.value = value;
      }

      public String getDesc() {
        return desc;
      }

      public void setDesc(String desc) {
        this.desc = desc;
      }
    }

}