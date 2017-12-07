package com.kapphk.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.Result;

/**
 * 公用的文件上传
 * @author exuan 15-4-29
 */
@RestController
@RequestMapping("/upload/")
public class FileUploadController{

	/**
	 * 公用的文件上传
	 */
	@RequestMapping("/fileUpload.do")
	public Result commonFileUpload(String photoData,String fileType,HttpServletRequest request){
		Result rs = new Result();
		try {
			rs = FileUploadUtils.commonFileUpload(photoData,fileType,request) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(20001) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 公用的文件上传（二进制流）
	 */
	@RequestMapping("/fileUploadByByte.do")
	public Result commonFileUploadByByte(HttpServletRequest request){
		Result rs = new Result();
		try {
			rs = FileUploadUtils.commonFileUploadByByte(request) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(20001) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
