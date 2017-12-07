package com.kapphk.web.utils;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.kapphk.base.bean.BaseModel;

/**
 * 封装的返回结果对象
 * @author Folo 2014年10月15日
 */
public class Result {
	
	/**
	 * 状态 10000 成功
	 */
	private int status;
	/**
	 * 消息内容 
	 */
	private String msg;
	/**
	 * 返回结果数据
	 */
	private JSONObject data;
	/**
	 * 服务器时间
	 */
	@JSONField(format=Contents.FORMIT)
	private Date time = null;
	
	public Result() {
		this.status = 10000;
		this.msg = "成功";
		this.data = new JSONObject();
	}
	
	public Result(String msg) {
		this.status = 10000;
		this.msg = msg;
	}
	
	public Result(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public Result setStatus(int status) {
		this.status = status;
		if(status == Contents.PARAMS_ERROR){
			this.msg = "参数错误";
		}else if(status == Contents.ERROR){
			this.msg = "失败,未知错误";
		}
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public JSONObject getData() {
		return data;
	}
	
	public Result put(String key, Object value){
		data.put(key, value);
		return this;
	}
	
	public Result put(BaseModel obj){
		this.data = (JSONObject) JSONObject.toJSON(obj);
		return this;
	}
	

	public Date getTime() {
		return time == null ? new Date() : time;
	}

	public Result setTime(Date time) {
		this.time = time;
		return this;
	}
	
	public Result setError(MSG msg){
		this.setMsg(msg.getMsg());
		this.setStatus(msg.getStatus());
		return this;
	}
	
	/**
	 * 消息枚举
	 * @author Folo 2014年10月20日
	 */
	public static enum MSG{
		/** 1000X 成功 */
		OK(10000, "成功"),
		/** 2000X 失败 */
		ERROR(20001, "失败,未知错误"),
		NOT_FOUND(20003, "{0},找不到该{1}！"),
		PARAMS_ERROR(20004, "参数错误,{0}"),
		/** 身份认证失效  */
		TOKEN_FAILURE(21001, "身份认证失效,请重新登录!"),
		/** 自定义错误 */
		CUS_ERROR(20005, "{0}"),
		/** 已存在 */
		EXIST_ERROR(30001,"已存在");
		
		private int status = 0;
		private String msg = null;
		MSG(int status, String msg){
			this.status = status;
			this.msg = msg;
		}
		
		public String getMsg(){
			return msg;
		}
		
		public int getStatus(){
			return status;
		}
	}
}
