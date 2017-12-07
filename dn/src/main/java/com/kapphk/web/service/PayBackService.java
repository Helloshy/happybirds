package com.kapphk.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayBackService {

	/**
	 * 支付宝回调接口
	 * @author zoneyu 16-3-15
	 */
	public String alipayBack(HttpServletRequest request, 
			HttpServletResponse response) throws Exception ;

	/**
	 * 微信回调接口
	 * @author zoneyu 16-3-15
	 */
	public String weiBack(HttpServletRequest request,
			HttpServletResponse response) throws Exception ;

	/**
	 * 微信回调接口
	 * @author exuan 16-11-29
	 */
	public String union(HttpServletRequest request, HttpServletResponse response) throws Exception ;

	public void executeBusiness(String order)throws Exception ;

}
