package com.kapphk.web.utils;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 上传文件到远程服务器
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class UploadUtil {
	
	private static final String CHARSET = "UTF-8";
	private static final Integer TIME_OUT = 50000;

	/**
	 * 通过HttpClient 上传文件
	 * @param file
	 * @return
	 */
	public static String upload(File file,String url) throws Exception{
		
		if(ValidateUtils.isBlank(url)){
			throw new RuntimeException("文件配置错误");
		}
		PostMethod postMethod = new PostMethod(url);
		try {
			Part[] parts = { new FilePart(file.getName(), file), new StringPart("fileName", file.getName()) };
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
			postMethod.setRequestEntity(mre);
			HttpClient client = new HttpClient();
			HttpMethodParams httpMethodParams = new HttpMethodParams();
			httpMethodParams.setContentCharset(CHARSET);
			postMethod.setParams(httpMethodParams);
			client.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);
			client.executeMethod(postMethod);
			String resultStr = new String(postMethod.getResponseBody(), CHARSET);
			UploadResult result = JSONObject.parseObject(resultStr, UploadResult.class);
			return result.getUrl();
		} catch (Exception e) {
			throw e;
		} finally {
			postMethod.releaseConnection();
		}
	}
	
	/**
	 * 上传图片文件
	 * @param file
	 * @throws Exception 
	 */
	public static String uploadImage(MultipartFile file) throws Exception{
		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem dfi = (DiskFileItem)cf.getFileItem(); 
        File fi = dfi.getStoreLocation();
        String url =  PropertiesUtil.getProperty("uploadImageURL");
        return upload(fi,url);
	}
	/**
	 * 上传图片文件
	 * @param file
	 * @throws Exception 
	 */
	public static String uploadImage(File file) throws Exception{
		String url =  PropertiesUtil.getProperty("uploadImageURL");
		return upload(file,url);
	}
	
	/**
	 * 上传一个文件
	 * @param file
	 * @throws Exception 
	 */
	public static String uploadFile(File file) throws Exception{
        String url =  PropertiesUtil.getProperty("uploadFileURL");
        return upload(file,url);
	}
	
	/**
	 * 上传一个文件
	 * @param file
	 * @throws Exception 
	 */
	public static String uploadFile(MultipartFile file) throws Exception{
		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem dfi = (DiskFileItem)cf.getFileItem(); 
        File fi = dfi.getStoreLocation();
        String url =  PropertiesUtil.getProperty("uploadFileURL");
        return upload(fi,url);
	}
	
	/**
	 * 上传文件结果
	 * @author Administrator
	 */
	static class UploadResult{
		private Integer error;
		private String url;
		public Integer getError() {
			return error;
		}
		public void setError(Integer error) {
			this.error = error;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}
}
