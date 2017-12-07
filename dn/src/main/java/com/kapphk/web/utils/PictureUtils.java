package com.kapphk.web.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
 * 上传多图片
 * @author dengwen 
 * 2016-9-3下午1:46:17
 */
@RequestMapping("/web/pictureutils/")
@RestController
public class PictureUtils {
	
	/**
	 * 多个图片上传
	 * @author dengwen 
	 * 2016-8-29上午10:43:43
	 */
	@RequestMapping("savePictures.htm")
	public List<String> saveFiles(@RequestParam("pictures")MultipartFile[] files,HttpServletRequest request){
		List<String> list = new ArrayList<String>();
		String reg = "^(.gif|.jpeg|.jpg|.bmp|.png)$";
		if (null != files && files.length != 0) {
			list = new ArrayList<String>();
			for (MultipartFile mf : files) {
				String fileName = mf.getOriginalFilename();
				fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				if(fileName.matches(reg))
					try {
						String str = uploadFile(mf.getBytes(),fileName,null,request);
						list.add(str);
					} catch (Exception e) {}
			}
		}
		return list;
	}
	
	/**
	 * 保存文件
	 * @author dengwen 
	 * 2016-8-29上午10:13:35
	 */
	private static String uploadFile(byte[] bytes, String type, String targetPath, HttpServletRequest request) throws Exception {
		String visitePath = "" ;//放问路径
		String rootPath = "/uploadIMG";
		if(!ValidateUtils.isEmpty(bytes)){
			String path2 = PictureUtils.class.getClassLoader().getResource("../../").getPath() ;
			String contextPath = request.getContextPath();
			String path = path2.replace(contextPath, rootPath);
			if(null == targetPath || StringUtils.isEmpty(targetPath)){
				path = path +"public/"+ getDate("yyyyMMdd");
				visitePath += rootPath+"/public/"+ getDate("yyyyMMdd");
			}else{ 
				path = path +targetPath + "/"+ getDate("yyyyMMdd");
				visitePath += rootPath + "/" + targetPath + "/"+ getDate("yyyyMMdd");
			}
			File file = new File(path);
			if(!file.exists()) file.mkdirs();
			String imgPath = "/" + getDate("yyyyMMddHHmmssSSS") + getRadom(3) + type;
			path += imgPath;
			visitePath += imgPath;
			FileOutputStream fos = new FileOutputStream(new File(path));
			fos.write(bytes);
			fos.close();
		}
		return visitePath ;
	};
	
	/**
	 * 获取当前时间自定义格式 如 (yyyy-MM-dd HH:mm:ss)
	 * @author dengwen 
	 * 2016-7-6上午11:00:33
	 */
	private static String getDate(String pattern){
		return new SimpleDateFormat(pattern).format(new Date());
	}
	
	/**
	 * 获取任意随机数
	 * @author dengwen 
	 * 2016-7-6下午3:34:32
	 */
	public static String getRadom(int i){
		String radom="";
		for (int j = 0; j < i; j++) {
			radom += (int)(Math.random()*10);
		}
		return radom;
	}
}