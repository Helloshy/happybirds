package com.kapphk.web.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kapphk.web.utils.Common.Settle;

public class Common {
	// 后台访问
	public static final String BACKGROUND_PATH = "WEB-INF/views";
	
	public static final String PORTAL_PATH = "WEB-INF/b2b";

	public static final String TEMPLET_EXCEL_PATH ="temp/抄表录入模板.xls";



    public static enum Settle {
    	/**气差费用*/
    	POORGASFEE, 
    	/**压车费用*/
    	PRESSFEE, 
    	/**结算金额*/
    	SETTLEACCOUNT
    } 
	
	  public static String getIpAddress(HttpServletRequest request) { 
		    String ip = request.getHeader("x-forwarded-for"); 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("Proxy-Client-IP"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("WL-Proxy-Client-IP"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("HTTP_CLIENT_IP"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getRemoteAddr(); 
		    } 
		    return ip; 
		  } 
	  
		/**
		 * html转议
		 * 
		 * @descript
		 * @param content
		 * @return
		 * @author LJN
		 * @date 2015年4月27日
		 * @version 1.0
		 */
		public static String htmltoString(String content) {
			if (content == null)
				return "";
			String html = content;
			html = html.replace("'", "&apos;");
			html = html.replaceAll("&", "&amp;");
			html = html.replace("\"", "&quot;"); // "
			html = html.replace("\t", "&nbsp;&nbsp;");// 替换跳格
			html = html.replace(" ", "&nbsp;");// 替换空格
			html = html.replace("<", "&lt;");
			html = html.replaceAll(">", "&gt;");

			return html;
		}

		/**
		 * html转议
		 * 
		 * @descript
		 * @param content
		 * @return
		 * @author LJN
		 * @date 2015年4月27日
		 * @version 1.0
		 */
		public static String stringtohtml(String content) {
			if (content == null)
				return "";
			String html = content;
			html = html.replace("&apos;", "'");
			html = html.replaceAll("&amp;", "&");
			html = html.replace("&quot;", "\""); // "
			html = html.replace("&nbsp;&nbsp;", "\t");// 替换跳格
			html = html.replace("&nbsp;", " ");// 替换空格
			html = html.replace("&lt;", "<");
			html = html.replaceAll("&gt;", ">");

			return html;
		}
		
		
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);
		return retStr;
	}
	

	/**
	 * json转list
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {  
        ArrayList<T> mList = new ArrayList<T>();  
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();  
        for(final JsonElement elem : array){  
            mList.add(new Gson().fromJson(elem, cls));  
        }  
        return mList;  
    }  

	/**
	 * 验证密码强度
	 * @param passwordStr
	 * @return
	 */
	public static Integer checkPassword(String passwordStr) {
		String regexZ = "\\d*";
		String regexS = "[a-zA-Z]+";
		String regexT = "\\W+$";
		String regexZT = "\\D*";
		String regexST = "[\\d\\W]*";
		String regexZS = "\\w*";
		String regexZST = "[\\w\\W]*";

		if (passwordStr.matches(regexZ)) {
			return 1;
		}
		if (passwordStr.matches(regexS)) {
			return 1;
		}
		if (passwordStr.matches(regexT)) {
			return 1;
		}
		if (passwordStr.matches(regexZT)) {
			return 2;
		}
		if (passwordStr.matches(regexST)) {
			return 2;
		}
		if (passwordStr.matches(regexZS)) {
			return 2;
		}
		if (passwordStr.matches(regexZST)) {
			return 3;
		}
		return 2;
	}
	
}
