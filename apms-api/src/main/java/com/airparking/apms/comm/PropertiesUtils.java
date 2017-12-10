package com.airparking.apms.comm;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取配置文件实例
 * @author luojihao
 *
 */
public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	private static Properties properties;
	
	static {
		try {
			properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
	
	public static String get(String key, String defaultValue){
		String val = get(key);
		return (val == null) ? defaultValue : val;
	}
}
