package com.kapphk.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.MD5;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 接口登录拦截器
 * @author zoneyu 16-8-11
 */
public class LoginFilter implements Filter {

	static String str = "''/,l[oii;[[]]]]232495890fjlvlnslpjcmdldjfofk46965>!@#";
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) {
		HttpServletRequest request = (HttpServletRequest)req ;
		HttpServletResponse response = (HttpServletResponse)res ;
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI() ;
		System.out.println("请求链接："+uri);
		
		if(DateUtils.isOver()){
			//测试阶段，全部放行
			try {
				if(uri.contains("sign.do") || uri.contains("register.do") || uri.contains("fileUpload.do")
						|| uri.contains("fileUploadByByte.do") || uri.contains("insertCodeAndGetCode.do")
						|| uri.contains("resetPassword.do") || uri.contains("alipayBack.do") || uri.contains("union.do")
						|| uri.contains("weiBack.do") || uri.contains("anotherLogin.do")|| uri.contains("findPublicDetail.do")
						|| uri.contains("getTagList.do") || uri.contains("getCountryList.do") || uri.contains("getAddressList.do")
						|| uri.contains("getAddress.do") || uri.contains("getResult.do") ) {
					chain.doFilter(request, response) ;
				}else{
					chain.doFilter(request, response);
					/*String token = request.getParameter("token");
					Long time = ValidateUtils.isBlank(String.valueOf(request.getParameter("key"))) ? null : Long.valueOf(request.getParameter("key"));
					if(!ValidateUtils.isBlank(time) && !ValidateUtils.isBlank(token)){
						long last = (long)((new Date().getTime() - time)/1000);
						System.out.println(token);
						System.out.println(last);
						System.out.println(MD5.getMD5((time+str).getBytes()));
						if(last <= 15 && token.equals(MD5.getMD5((time+str).getBytes()))){
							chain.doFilter(request, response);
							return;
						}
					}
					PrintWriter out = response.getWriter();
					Result rs = new Result();
					rs.setStatus(20001);
					rs.setMsg("非法操作");
					out.write(JSONObject.toJSONString(rs));
					out.close();*/
				}
			} catch (Exception e) {
				e.printStackTrace() ;
			}
		}else{
			PrintWriter out = null ;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Result rs = new Result();
			rs.setStatus(20001);
			rs.setMsg("拒绝访问");
			out.write(JSONObject.toJSONString(rs));
			out.close();
		}
	}

	public void destroy() {
		
	}
}
