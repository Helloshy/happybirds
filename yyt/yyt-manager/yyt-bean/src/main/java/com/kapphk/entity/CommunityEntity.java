package com.kapphk.entity;

import com.kapphk.yyt.bean.Community;

public class CommunityEntity extends Community{
	
	private String companyName;
	
	
	private String realName;
	
	private String hallName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	
}
