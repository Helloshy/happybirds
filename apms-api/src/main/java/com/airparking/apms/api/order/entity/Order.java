package com.airparking.apms.api.order.entity;

import java.io.Serializable;
import java.util.Date;

import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-07-26.
 */
public class Order extends AbstractEntity implements Serializable {
    private Long id;
    private Long parkId;
    private String tradeNo;
    private String rateId;
    // 订单类型：0-临停，1-月保， 2.......
    private Byte type;
    private String plateNo;
    // 入场时间
    private Date startTime;
    // 出场时间
    private Date endTime;
    private Long totalTime;
    private Integer totalAmount;
    private Integer payAmount;
    // 订单状态
    private Integer orderStatus;

    private String additions;
    
    private Long couponid;
    
    private String code;
    
    private String remark;
    
    private String cardNo;
    
    private Boolean isSend;

    public String getCardNo() {
      return cardNo;
    }

    public void setCardNo(String cardNo) {
      this.cardNo = cardNo;
    }

    public Boolean getIsSend() {
      return isSend;
    }

    public void setIsSend(Boolean isSend) {
      this.isSend = isSend;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public Long getCouponid() {
      return couponid;
    }

    public void setCouponid(Long couponid) {
      this.couponid = couponid;
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

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public void setType(Byte type){
        this.type = type;
    }

    public Byte getType(){
        return this.type;
    }

    public void setPlateNo(String plateNo){
        this.plateNo = plateNo;
    }

    public String getPlateNo(){
        return this.plateNo;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }

    public Date getStartTime(){
        return this.startTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }

    public Date getEndTime(){
        return this.endTime;
    }

    public void setTotalTime(Long totalTime){
        this.totalTime = totalTime;
    }

    public Long getTotalTime(){
        return this.totalTime;
    }

    public void setTotalAmount(Integer totalAmount){
        this.totalAmount = totalAmount;
    }

    public Integer getTotalAmount(){
        return this.totalAmount;
    }

    public void setPayAmount(Integer payAmount){
        this.payAmount = payAmount;
    }

    public Integer getPayAmount(){
        return this.payAmount;
    }

    public void setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus(){
        return this.orderStatus;
    }

    public String getAdditions() {
        return additions;
    }

    public void setAdditions(String additions) {
        this.additions = additions;
    }

    public enum OrderStatus {
        START(1, "start"),
        PAID(2, "paid"),
        AUTOEND(4, "autoend"),
        DISCARD(8, "discard"),
        ENDBYMAN(16, "endbyman"),
        SECONDPAID(32,"secondpaid");

        private int value;
        private String desc;

        OrderStatus(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum OrderType {
        OFFLINE(0),//临停
        MONTH(1),//月保
        AIRPARKING(2),//共享
    	WHITELIST(3);//白名单

        private int value;

        OrderType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}