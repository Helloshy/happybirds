package com.kapphk.entity;

import java.math.BigDecimal;

import com.kapphk.yyt.bean.UserOrder;

public class UserOrderEntity extends UserOrder {

	private BigDecimal preBalance;

	private String address;
	private String unit;
	private String realName;
	private String tel ;
	private String recordTag;
	
	private String communityName;

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public BigDecimal getPreBalance() {
		return preBalance;
	}

	public void setPreBalance(BigDecimal preBalance) {
		this.preBalance = preBalance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRecordTag() {
		return recordTag;
	}

	public void setRecordTagType(String recordTag) {
		this.recordTag = recordTag;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setRecordTag(String recordTag) {
		this.recordTag = recordTag;
	}
	
}
