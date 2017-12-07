package com.kapphk.web.controller ;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.service.PayBackService;

/**
 * 支付回调控制层
 * @author exuan 16-3-15
 */
@RestController
@RequestMapping("/payBack/")
public class PayBackController {

	@Autowired
	private PayBackService service ;
	
	/**
	 * 支付宝回调接口
	 * @author exuan 16-3-15
	 */
	@RequestMapping("alipayBack.do")
	public void alipayBack(HttpServletRequest request,HttpServletResponse response) {
		String status = "fail" ;
		PrintWriter out = null ;
		try {
			out = response.getWriter() ;
			status = service.alipayBack(request,response) ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.write(status) ;
				out.close() ;
			}
		}
	}
	
	/**
	 * 微信回调接口
	 * @author exuan 16-3-15
	 */
	@RequestMapping("weiBack.do")
	public void weiBack(HttpServletRequest request,HttpServletResponse response) {
		String status = "FAIL" ;
		PrintWriter out = null ;
		try {
			out = response.getWriter() ;
			status = service.weiBack(request,response) ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.write(status) ;
				out.close() ;
			}
		}
	}
	
	/**
	 * 微信回调接口
	 * @author exuan 16-11-29
	 */
	@RequestMapping("union.do")
	public void union(HttpServletRequest request,HttpServletResponse response) {
		String status = "FAIL" ;
		PrintWriter out = null ;
		try {
			out = response.getWriter() ;
			status = service.union(request,response) ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.write(status) ;
				out.close() ;
			}
		}
	}
}
