package com.kapphk.web.controller;

import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import com.kapphk.web.utils.Result;

/**
 * 抽象BaseController，用于继承
 * @author exuan 2014-10-13
 */
public abstract class BaseController<M, T> {
	Result ret = null;
	
	@PostConstruct
	public void init(){
		ret =  new Result();
		ret.put("tttt", "ccccc");
	}
	
	/**
	 * 写出数据
	 */
	public void writerJson(HttpServletResponse response ,String json){
		response.setCharacterEncoding("utf-8") ;
		response.setContentType("text/html;charset=utf-8") ;
		PrintWriter out = null;
		try {
			out = response.getWriter() ;
			out.write(json) ;
			if(out != null){
				out.flush() ;
				out.close() ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out = null ;
		}
	}
	
}
