package com.kapphk.entity;

import java.math.BigDecimal;

import com.kapphk.yyt.bean.CompanyHall;

public class CompanyHallEntity extends CompanyHall {

	private BigDecimal distance;
	
	private String companyName;
	
	private BigDecimal totalMoney;
	
	private BigDecimal MonthMoney;
	
	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getMonthMoney() {
		return MonthMoney;
	}

	public void setMonthMoney(BigDecimal monthMoney) {
		MonthMoney = monthMoney;
	}
	
}
