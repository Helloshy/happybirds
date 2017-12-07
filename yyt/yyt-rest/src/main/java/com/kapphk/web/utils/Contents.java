package com.kapphk.web.utils;

/**
 * 配置的系统全局变量
 * @author Folo 2014年10月15日
 */
public class Contents {
	/****************************时间*************************/
	/** 默认时间格式化 "yyyy-MM-dd HH:mm:ss" */
	public static final String FORMIT = "yyyy-MM-dd HH:mm:ss";// 全局的时间格式
	/**  默认日期格式化 "yyyy-MM-dd" */
	public static final String FORMITD = "yyyy-MM-dd";// 全局的日期格式
	
	
	/****************************图片*************************/
	/** 图片文件存储目录 */
	public static final String FILEPARH = "/usr/local/uploadFiles/qcy/";// 文件存储目录
	public static final String FILEPARH_CODE = "/usr/local/uploadFiles/qcy/code/";// 文件存储目录
	public static final String FILEPARH_IMG = "/usr/local/uploadFiles/qcy/image/";// 图片存储目录
	public static final int IMG_WIDTH = 200;// 小图默认宽
	public static final int IMG_HEIGHT = 200;// 小图默认高
	/****************************测试*************************/
//	public static final String TEST_MOBILE_PATH = "http://localhost/kapphk-qcy/m/";//测试手机端接口url
	public static final String TEST_WEB_PATH = "http://localhost/kapphk-qcy/";//测试手机端接口url
	
	public static final String TEST_MOBILE_PATH = "http://112.124.19.183/kapphk-qcy/m/";//测试手机端接口url
	// desc key
	public static final String DESCKEY = "2014Kapphk20140723222912QiHuiWangLuoDescKey0123456789";
	
	
	public static final int OK = 10000;
	public static final int ERROR = 20001;
	public static final int NOT_FOUND = 20003;
	public static final int PARAMS_ERROR= 21001;
	public static final int TOKEN_FAILURE = 10000;
	public static final int VALUE_EMPTY = 20006;
	public static final int EXIST = 20007;
	public static final int NOT_EXIST = 20009;
	public static final int STOP = 20008;
	public static final int CUS_ERROR = 20005;
	
	/**数据状态 1:正常 2:已失效 -1:已删除**/
	public static final int MODEL_STATUS_l1 = -1;
	/**数据状态 0:停用 1:正常 2:已失效 -1:已删除**/
	public static final int MODEL_STATUS_0 = 0;
	/**数据状态 1:正常 2:已失效 -1:已删除**/
	public static final int MODEL_STATUS_1 = 1;
	/**数据状态 1:正常 2:已失效 -1:已删除**/
	public static final int MODEL_STATUS_2 = 2;
	
}
