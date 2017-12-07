package com.kapphk.base.bean;

import java.util.Date;

/**
 * model模版类 用于继承
 * @author exuan 15-10-19
 */
public class BaseModel {
	
	/**
	 * create_time 创建时间
	 */
	private Date createTime;

	/**
	 * status 数据状态 1:正常 2:已失效 -1:已删除
	 */
	private Integer status;

	/**
	 * state 数据状态 1:正常 2:已失效 -1:已删除
	 */
	private Integer state;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
