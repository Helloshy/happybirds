package com.kapphk.entity;

import com.kapphk.yyt.bean.UserNumber;

public class UserNumberEntity extends UserNumber {
	
	private Long uid;
	
	private String address;
	
	private Integer lastCount;
	
	private String communityName;
	
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLastCount() {
		return lastCount;
	}

	public void setLastCount(Integer lastCount) {
		this.lastCount = lastCount;
	}
	
	
	
}
