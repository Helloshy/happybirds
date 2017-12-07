package com.kapphk.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/** 
*
* @author EXIA
* @version 1.0
* @createDate 2016年9月23日 上午10:47:57 
*
*/
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        //这里的handler就是处理器适配器要执行的handler对象，此对象中只有一个方法
        ex.printStackTrace();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.setViewName("error/500");

        return modelAndView;
    }
}
