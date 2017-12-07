package com.kapphk.web.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kapphk.web.utils.Result.MSG;



/**
 * 文件上传工具类
 * @author zoneyu 14-6-9
 */
public class FileUploadUtils {

	private FileUploadUtils(){}

	/**
	 * 公用的文件上传
	 */
	public static Result commonFileUpload(String photoData, String fileType,HttpServletRequest request) {
		Result rs = new Result();
		String rootPath = "/uploadIMG" ;
		String fileName = DateUtils.getLocalDate("yyyyMMddHHmmss") + DataUtils.getRadom(8) ;
		String path2 = FileUploadUtils.class.getClassLoader().getResource("../../").getPath() ;
		String contextPath = request.getContextPath() ;
		String path = path2.replace(contextPath, rootPath) ;//d:webserver/tomcat7/webapps/uploadIMG
		ZipUtils zip = new ZipUtils() ;
		if(!ValidateUtils.isBlank(photoData) && !ValidateUtils.isBlank(fileType)){
			if("1".equals(fileType)){//头像,目录/upload/logoPath
				//d:webserver/tomcat7/webapps/uploadIMG/upload/logoPath/20151224
				String dir = "/upload/logoPath" + DateUtils.getLocalDate("yyyyMMdd") ;
				path = path + dir ;
				File file = new File(path) ;
				if(!file.exists()){
					file.mkdirs() ;
				}
				String targetPath = path +"/"+ fileName +".png" ;
				boolean b = ImageBase64Util.GenerateImage(photoData, targetPath) ;
				if(b){
					rs.put("path", rootPath+dir+"/"+fileName+".png") ;
					zip.compressPic(path+"/", path+"/", fileName +".png", fileName +".png", 300, 300, true) ;
				}else{
					rs.setMsg("上传失败") ;
					rs.setStatus(MSG.ERROR.getStatus()) ;
				}
			}else if("2".equals(fileType) || "3".equals(fileType)){//证件
				//d:webserver/tomcat7/webapps/uploadIMG/upload/papers/20151224
				String dir = "/upload/papers" + DateUtils.getLocalDate("yyyyMMdd") ;
				path = path + dir ;
				File file = new File(path) ;
				if(!file.exists()){
					file.mkdirs() ;
				}
				String targetPath_s = path +"/"+ fileName +"_s.png" ;
				String targetPath_l = path +"/"+ fileName +"_l.png" ;
				boolean b = ImageBase64Util.GenerateImage(photoData, targetPath_s) ;
				boolean b2 = ImageBase64Util.GenerateImage(photoData, targetPath_l) ;
				if(b && b2){
					rs.put("path", rootPath+dir+"/"+fileName+".png") ;
					zip.compressPic(path+"/", path+"/", fileName +"_s.png", fileName +"_s.png", 300, 300, true) ;
				}else{
					rs.setMsg("上传失败") ;
					rs.setStatus(MSG.ERROR.getStatus()) ;
				}
//			}
//			else if("3".equals(fileType)){//轮播图
//				//d:webserver/tomcat7/webapps/uploadIMG/upload/banner/20151224
//				String dir = "/upload/banner" + DateUtils.getLocalDate("yyyyMMdd") ;
//				path = path + dir ;
//				File file = new File(path) ;
//				if(!file.exists()){
//					file.mkdir() ;
//				}
//				String targetPath = path +"/"+ fileName +".png" ;
//				boolean b = ImageBase64Util.GenerateImage(photoData, targetPath) ;
//				if(b){
//					rs.put("path", rootPath+dir+"/"+fileName+".png") ;
//					zip.compressPic(path+"/", path+"/", fileName +"_s.png", fileName +"_s.png", 300, 300, true) ;
//				}else{
//					rs.setMsg("上传失败") ;
//					rs.setStatus(MSG.ERROR.getStatus()) ;
//				}
			}else{
				rs.setMsg("类型不正确") ;
				rs.setStatus(MSG.ERROR.getStatus()) ;
			}
		}
		return rs ;
	}

	static long maxSize = 5240880;
	
	/**
	 * 公用的文件上传（二进制流）
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Result commonFileUploadByByte(HttpServletRequest request) throws Exception {
		Result rs = new Result();
		String visitePath = "" ;
		String rootPath = "/uploadIMG" ;
		Map<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		
		String dirName = request.getParameter("dir");
		if (dirName == null){
			dirName = "image";
		}
		
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request ;
		Iterator iter = multiRequest.getFileNames();
		while (iter.hasNext()) {
			MultipartFile file = multiRequest.getFile((String) iter.next());
			if(file != null){
				String fileName = file.getOriginalFilename();
				//判断文件大小
				if(file.getSize() > maxSize) {
					rs.setStatus(21001) ;
					rs.setMsg("上传文件大小超过5M") ;
					return rs ;
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					rs.setStatus(21001) ;
					rs.setMsg("上传文件扩展名是不允许的扩展名"+fileExt+",只允许" + extMap.get(dirName) + "格式") ;
					return rs ;
				}
				String path2 = FileUploadUtils.class.getClassLoader().getResource("../../").getPath() ;  
				String contextPath = request.getContextPath() ;
				String path = path2.replace(contextPath, rootPath) ;
				
				String path1 = "/upload/public/"+DateUtils.getLocalDate("yyyyMMdd") ;//文件的上级目录
				path = path + path1;
				File file1 = new File(path) ;
				if(!file1.exists()){//不存在就创建目录
					file1.mkdirs() ;
				}
				
				//文件路径
				String fileName2 = DateUtils.getLocalDate("yyyyMMddHHmmss")+DataUtils.getRadom(8)+".png" ;
				String filePath = path + "/" + fileName2;
				visitePath += rootPath+path1+"/"+fileName2+"|" ;//访问路径
				file.transferTo(new File(filePath));
			}
		}
		if(!ValidateUtils.isBlank(visitePath)){
			visitePath = visitePath.substring(0, visitePath.length() - 1) ;
		}
		System.out.println("路径："+visitePath);
		rs.put("path", visitePath) ;
		return rs ;
	}
	
	/**
	 * 文件上传
	 * @param bytes
	 * @param targetPath
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile multipartFile, String targetPath, 
			 HttpServletRequest request) throws Exception {
		String visitePath = "" ;//放问路径
		
		String rootPath = "/upload" ;
		String fileName = multipartFile.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		String path2 = FileUploadUtils.class.getClassLoader().getResource("../../").getPath() ;
		String contextPath = request.getContextPath() ;
		String path = path2.replace(contextPath, rootPath);
		path = path +"/"+ targetPath ;
		File file = new File(path) ;
		if(!file.exists()) file.mkdirs() ;
		String radom = DateUtils.getLocalDate("yyyyMMddHHmmss") + DataUtils.getSixRadom() ;
		String saveFileName = radom +"."+fileExt;
		visitePath = path+"/"+saveFileName ;
		copyFile(multipartFile.getBytes(), visitePath) ; 
		return rootPath+"/"+targetPath+"/"+saveFileName;
	} ;
	
	/**
	 * 图片上传（后台用）
	 * @param logPath           //文件原路径
	 * @param targetPath        //目标文件夹，比如: photo/user
	 * @param type              //类型，0：不压缩，1：头像，压缩成300*300，2：轮播图，压缩成480*210
	 * @return
	 */
	public static String uploadFile(byte[] bytes, String targetPath, 
			String type, HttpServletRequest request) throws Exception {
		String visitePath = "" ;//放问路径
		
		String rootPath = "/uploadIMG" ;
		
		if(!ValidateUtils.isEmpty(bytes) && !ValidateUtils.isBlank(type)){
			
			//目标文件路径
			String targetPath_l = "";
			String targetPath_s = "";
			
			String path2 = FileUploadUtils.class.getClassLoader().getResource("../../").getPath() ;
			String contextPath = request.getContextPath() ;
			String path = path2.replace(contextPath, rootPath);
			
			if("0".equals(type)){//证件类，同意放在papers目录
				path = path +"/"+ targetPath ;
				File file = new File(path) ;
				if(!file.exists()) file.mkdirs() ;
				
				String radom = DateUtils.getLocalDate("yyyyMMddHHmmss") + DataUtils.getSixRadom() ;
				String filename_l = radom+"-l.png" ;
				String filename_s = radom+"-s.png" ;
				visitePath = targetPath+"/"+filename_s ;
				targetPath_l = path +"/"+filename_l ; //大图
				targetPath_s = path +"/"+filename_s ; //小图
				copyFile(bytes, targetPath_l) ; //大图
				copyFile(bytes, targetPath_s, path,filename_s,300,300) ;
			}else if("1".equals(type)){//头像
				path = path + targetPath ;
				File file = new File(path) ;
				if(!file.exists()) file.mkdirs() ;
				
				String radom = DateUtils.getLocalDate("yyyyMMddHHmmss") + DataUtils.getSixRadom() ;
				String fileName = radom+".png" ;
				targetPath_s = path + "/" + fileName ;
				visitePath = targetPath+"/"+fileName ;
				copyFile(bytes, targetPath_s, path,fileName,300,300) ;
			}else if("2".equals(type)){//轮播图
				path = path + targetPath ;
				File file = new File(path) ;
				if(!file.exists()) file.mkdirs() ;
				
				String radom = DateUtils.getLocalDate("yyyyMMddHHmmss") + DataUtils.getSixRadom() ;
				String fileName = radom+".png" ;
				targetPath_s = path + "/" + fileName ;
				visitePath = targetPath+"/"+fileName ;
				copyFile(bytes, targetPath_s, path,fileName);
			}
		}
		return visitePath ;
	} ;
	
	/**
	 * 写出数据到文件
	 * @param srcPath                //原文件路径
	 * @param targetPath             //目标文件路径
	 * @param args                   //可变参数，用于图片压缩，传入则压缩，不传，不压缩。参数位置必须是，第一个位置为path，
	 * 类型为String（比如：../gsf-web/photo/user）,
	 * 第二个位置为文件名，类型为String（比如：20141222345678-l.png）,第三个为图片的宽度，类型为int（比如：300*300）
	 */
	public static void copyFile(byte[] bytes,String targetPath,Object... args) throws Exception {
		FileOutputStream outStream = new FileOutputStream(new File(targetPath));
		outStream.write(bytes) ;
		outStream.close() ;
		
		if(!ValidateUtils.isempty(args)){
			if(args.length == 4){
				String path = (String) args[0] ;
				String fileName = (String) args[1] ;
				int width = (Integer) args[2] ;
				int height = (Integer) args[3] ;
				//添加图片压缩
				ZipUtils zip = new ZipUtils() ;
				zip.compressPic(path+"/", path+"/", fileName, fileName, width, height, true) ;
			}
		}
	}
}
