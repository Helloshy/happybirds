package com.kapphk.web.utils;

import com.google.gson.*;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.*;


public class Common {
	// 后台访问
	public static final String BACKGROUND_PATH = "WEB-INF/views";
	
	public static final String PORTAL_PATH = "WEB-INF/yyt";
	
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
	
	
	public static Properties getProperties(){
		Properties prop = new Properties();
		//以下方法读取属性文件会缓存问题
//		InputStream in = PropertiesUtils.class
//				.getResourceAsStream("/config.properties");
		try {
			String savePath = URLDecoder.decode(Common.class.getResource("/config.properties").getPath(),"UTF-8");
			InputStream in =new BufferedInputStream(new FileInputStream(savePath));  
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return prop;
	}
	
	
	public static void modifyProperties(String key, String value) {
		try {
			// 从输入流中读取属性列表（键和元素对）
			Properties prop = getProperties();
			prop.setProperty(key, value);
			String path = URLDecoder.decode(Common.class.getResource("/config.properties").getPath(),"UTF-8");
			FileOutputStream outputFile = new FileOutputStream(path);
			prop.store(outputFile, "modify");
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
        	Gson gson = new GsonBuilder()  
        			  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
        			  .create();  
            mList.add(gson.fromJson(elem, cls));  
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
	
	/**
	 * 上传文件
	 * @param files
	 * @param request
	 * @return
	 */
    public static String upload(CommonsMultipartFile files,String targetPath,HttpServletRequest request){  
    	String savePath = null;
            if(!files.isEmpty()){  
            	String rootPath = "/Uploads/" + targetPath ;
        		String fileName = DateUtils.getLocalDate("yyyyMMddHHmmss") + DataUtils.getRadom(8) ;
        		String path2 = FileUploadUtils.class.getClassLoader().getResource("../../").getPath() ;
        		String contextPath = request.getContextPath() ;
        		String path = path2.replace(contextPath, rootPath) ;//d:webserver/tomcat7/webapps/uploadIMG
        		File file = new File(path) ;
        		if(!file.exists()){
        			file.mkdirs() ;
        		}
        		String outFile = path +"/"+ fileName +".pdf" ;
        		savePath = rootPath + "/" + fileName + ".pdf";
                try {  
                    //拿到输出流，同时重命名上传的文件  
                    FileOutputStream os = new FileOutputStream(outFile);  
                    //拿到上传文件的输入流  
                    FileInputStream in = (FileInputStream) files.getInputStream();  
                      
                    //以写字节的方式写文件  
                    int b = 0;  
                    while((b=in.read()) != -1){  
                        os.write(b);  
                    }  
                    os.flush();  
                    os.close();  
                    in.close();  
                      
                } catch (Exception e) {  
                    e.printStackTrace();  
                    System.out.println("上传出错");  
                }  
        }  
        return savePath;  
    }
    
    
    
	public static String uploadFile(byte[] bytes, String targetPath, String suffix,
			 HttpServletRequest request) throws Exception {
		String visitePath = "" ;//放问路径
		
		String rootPath = "/Uploads" ;
		
		if(!ValidateUtils.isEmpty(bytes)){
			
			//目标文件路径
			String targetPath_s = "";
			
			String path2 = FileUploadUtils.class.getClassLoader().getResource("../../").getPath() ;
			String contextPath = request.getContextPath() ;
			String path = path2.replace(contextPath, rootPath);
			path = path + targetPath ;
			File file = new File(path) ;
			if(!file.exists()) file.mkdirs() ;
			
			String radom = DateUtils.getLocalDate("yyyyMMddHHmmss") + DataUtils.getSixRadom() ;
			String fileName = radom+suffix ;
			targetPath_s = path + "/" + fileName ;
			visitePath = targetPath+"/"+fileName ;
			FileUploadUtils.copyFile(bytes, targetPath_s, path,fileName);
		}
		return rootPath + "/" + visitePath ;
	} ;
    
    /**
     * xml转json
     * @param xml
     * @return
     */
    public static String xmltoJson(String xml) {  
        XMLSerializer xmlSerializer = new XMLSerializer();  
        return xmlSerializer.read(xml).toString();  
    }  
    

}
