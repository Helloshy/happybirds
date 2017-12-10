package com.airparking.apms.api.park.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * Created by ryan on 2016-08-03.
 */
public class Park extends AbstractEntity implements Serializable {
    private Long id;
    private String pname;
    private Long tenancyId;
    private Double longitude;
    private Double latitude;
    private Long districtId;
    private String address;
    private String contact;
    private String mobile;
    // 允许滞留时间(分钟)
    private Integer freeTime;
    // 1-允许临保进场，0- 不允许
    private Byte flags;
    // 月保过期保留时间（天）
    private Byte monthDiedDays;
    private Date noTempStartDate;
    private Date noTempEndDate;
    private Integer totalSpace;
    private Integer tempSpace;
    private Integer fixSpace;
    private Boolean isDisabled;
    private Boolean isDeleted;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
	private Integer isDayParting;
	private Integer isOnlinePay;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setPname(String pname){
        this.pname = pname;
    }

    public String getPname(){
        return this.pname;
    }

    public void setTenancyId(Long tenancyId){
        this.tenancyId = tenancyId;
    }

    public Long getTenancyId(){
        return this.tenancyId;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public Double getLongitude(){
        return this.longitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public Double getLatitude(){
        return this.latitude;
    }

    public void setDistrictId(Long districtId){
        this.districtId = districtId;
    }

    public Long getDistrictId(){
        return this.districtId;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    public String getContact(){
        return this.contact;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getMobile(){
        return this.mobile;
    }

    public void setFreeTime(Integer freeTime){
        this.freeTime = freeTime;
    }

    public Integer getFreeTime(){
        return this.freeTime;
    }

    public void setFlags(Byte flags){
        this.flags = flags;
    }

    public Byte getFlags(){
        return this.flags;
    }

    public void setMonthDiedDays(Byte monthDiedDays){
        this.monthDiedDays = monthDiedDays;
    }

    public Byte getMonthDiedDays(){
        return this.monthDiedDays;
    }

    public void setNoTempStartDate(Date noTempStartDate){
        this.noTempStartDate = noTempStartDate;
    }

    public Date getNoTempStartDate(){
        return this.noTempStartDate;
    }

    public void setNoTempEndDate(Date noTempEndDate){
        this.noTempEndDate = noTempEndDate;
    }

    public Date getNoTempEndDate(){
        return this.noTempEndDate;
    }

    public void setTotalSpace(Integer totalSpace){
        this.totalSpace = totalSpace;
    }

    public Integer getTotalSpace(){
        return this.totalSpace;
    }

    public void setTempSpace(Integer tempSpace){
        this.tempSpace = tempSpace;
    }

    public Integer getTempSpace(){
        return this.tempSpace;
    }

    public void setFixSpace(Integer fixSpace){
        this.fixSpace = fixSpace;
    }

    public Integer getFixSpace(){
        return this.fixSpace;
    }

    public void setIsDisabled(Boolean isDisabled){
        this.isDisabled = isDisabled;
    }

    public Boolean getIsDisabled(){
        return this.isDisabled;
    }

    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }

    public Boolean getIsDeleted(){
        return this.isDeleted;
    }

    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }

    public Date getCreatedDate(){
        return this.createdDate;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public String getCreatedBy(){
        return this.createdBy;
    }

    public void setUpdatedDate(Date updatedDate){
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate(){
        return this.updatedDate;
    }

    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy(){
        return this.updatedBy;
    }

	public Integer getIsDayParting() {
		return isDayParting;
	}

	public void setIsDayParting(Integer isDayParting) {
		this.isDayParting = isDayParting;
	}

	public Integer getIsOnlinePay() {
		return isOnlinePay;
	}

	public void setIsOnlinePay(Integer isOnlinePay) {
		this.isOnlinePay = isOnlinePay;
	}

	public enum OnlineStatus {

		NORIGHT(0,"关闭权限"), RIGHT(1,"开启权限"), OPEN(2,"开启功能"), CLOSE(3,"关闭功能");

		private int key;
		private String value;

		private OnlineStatus(int key,String value){
			this.key = key ;
			this.value = value ;
		}

		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum DayParting {

		NORIGHT(0,"关闭权限"), RIGHT(1,"开启权限"), OPEN(2,"开启功能"), CLOSE(3,"关闭功能");

		private int key;
		private String value;

		private DayParting(int key,String value){
			this.key = key ;
			this.value = value ;
		}

		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}