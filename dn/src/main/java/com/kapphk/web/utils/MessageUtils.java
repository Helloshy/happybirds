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
	
private static String url = "http://www.ucpaas.com/maap/sms/code" ;   //请求URL
	
	//需要替换的项    ----- start -----  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private static String sid = "a2603ddf957c3e3e1349634c47478921" ;                     //主账户id
	private static String appId = "47dc2169d1d448c0a2921bbfdbeafd80" ;                     //应用id
	private static String token = "709fb99f1b1c7cd5063091d40f434acf" ;               //账户授权令牌
	private static String tempId = "33308";	//短信模板
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
		System.out.println("手机号： "+phone);
		String msg = random ;
		HttpClient httpClient = new HttpClient();
		String time = DateUtils.getLocalDate("yyyyMMddHHmmssSSS");
		String auth = MD5.getMD5(sid+time+token);
        String content = msg ;
		PostMethod postMethod = new UTF8PostMethod(url);
		
		NameValuePair[] data = {
					new NameValuePair("sid", sid),
					new NameValuePair("appId", appId),
					new NameValuePair("time", time),
					new NameValuePair("sign", auth),
					new NameValuePair("to",phone ),
					new NameValuePair("templateId",tempId ),
					new NameValuePair("param",content )};
		postMethod.setRequestBody(data);
		int statusCode = httpClient.executeMethod(postMethod);
		
		if (statusCode == HttpStatus.SC_OK) {
			String sms = postMethod.getResponseBodyAsString();
			System.out.println("result:" + sms);
		}
		System.out.println("statusCode="+statusCode);
	}

	public static class UTF8PostMethod extends PostMethod{     
	    public UTF8PostMethod(String url){     
	    super(url);     
	    }     
	    @Override     
	    public String getRequestCharSet() {     
	        //return super.getRequestCharSet();     
	        return "UTF-8";     
	    }  
	} 
	
}
