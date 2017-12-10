package com.airparking.apms.api.monthrentrate.entity;

import com.airparking.core.base.entity.AbstractEntity;

import java.io.Serializable;


/**
 * @author
 * @create 2017-04-24-15:33
 */

public class MonthrentPeriod extends AbstractEntity implements Serializable {

	private Long id;

	private Long monthrentrateId;

	private Long parkId;

	private Integer weekDay; // 'SUN(1),MON(2),TUES(4),WED(8),THUR(16),FRI(32),SAT(64)'

	private Boolean isHoliday;

	private Integer typ;//'1:休息日，2:工作日'

	private String startTime;

	private String endTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMonthrentrateId() {
		return monthrentrateId;
	}

	public void setMonthrentrateId(Long monthrentrateId) {
		this.monthrentrateId = monthrentrateId;
	}

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public Integer getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}

	public Boolean getHoliday() {
		return isHoliday;
	}

	public void setHoliday(Boolean holiday) {
		isHoliday = holiday;
	}

	public Integer getTyp() {
		return typ;
	}

	public void setTyp(Integer typ) {
		this.typ = typ;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
