package com.kapphk.web.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件工具类
 * @author Administrator
 */
public class PropertiesUtil {
	
	private final static String DEFULT_PROPERTIES = "/config.properties";

	private static Properties props;

	private static void readProperties(String fileName) {
		try {
			props = new Properties();
			InputStream fis = PropertiesUtil.class.getResourceAsStream(fileName);
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取配置文件值
	 * @param fileName 配置文件路径
	 * @param key 属性名
	 * @return
	 */
	public static String getProperty(String fileName ,String key) {
		try {
			readProperties(fileName);
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取配置文件属性值
	 * @param key 属性名
	 * @return
	 */
	public static String getProperty(String key) {
		try {
			return getProperty(DEFULT_PROPERTIES,key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取配置文件属性值
	 * @param key 属性名
	 * @return
	 */
	public static String getFileServer() {
		try {
			String key= "fileServerURL" ;
			return getProperty(DEFULT_PROPERTIES,key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(getFileServer());
		
	}
}
