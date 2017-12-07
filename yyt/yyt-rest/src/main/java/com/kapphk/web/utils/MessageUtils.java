package com.kapphk.web.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 发送短信接口
 * @author zoneyu 14-6-19
 */
public class MessageUtils {
	
private static String url = "http://210.5.158.31/hy/" ;   //请求URL
	
	//需要替换的项    ----- start -----  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private static String uid = "80801" ;                     //用户id
	private static String code = "tbbt" ;                     //企业代码
	private static String password = "tbbt77" ;               //密码
	//需要替换的项    -----   end -----  ↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 
	
	public static void main(String[] args) throws Exception {
		sendMessage("18613089064","1235") ;
	}
	
	/**
	 * 发送短信
	 * @param phone     手机号
	 * @param random    验证码
	 * @throws Exception 
	 */
	public static void sendMessage(String phone ,String random) throws Exception{
		System.out.println("验证码："+random);
		String msg = "您本次的验证码为："+random ;
		HttpClient httpClient = new HttpClient();
		String auth = MD5.getMD5(code+password);
        String content = java.net.URLEncoder.encode(msg , "gbk");
		PostMethod postMethod = new PostMethod(url);
		
		NameValuePair[] data = {
					new NameValuePair("uid", uid),
					new NameValuePair("auth", auth),
					new NameValuePair("mobile", phone),
					new NameValuePair("expid", "0"),
					new NameValuePair("msg",content ) };
		postMethod.setRequestBody(data);
		int statusCode = httpClient.executeMethod(postMethod);
			
		if (statusCode == HttpStatus.SC_OK) {
			String sms = postMethod.getResponseBodyAsString();
			System.out.println("result:" + sms);
		}
		System.out.println("statusCode="+statusCode);
	}
	
}
