package com.kapphk.web.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


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
	private Integer state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
