package com.kapphk.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.web.context.request.RequestContextHolder;    
import org.springframework.web.context.request.ServletRequestAttributes; 
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kapphk.anotation.SystemLog;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.SysLogService;
import com.kapphk.web.utils.Common;

@Aspect    
@Component    
public  class SystemLogAspect {    
     //注入Service用于把日志保存数据库    
     @Autowired
     private SysLogService logService;    
     //本地异常日志记录对象    
     private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);    
    
    
    //Controller层切点    
     @Pointcut("@annotation(com.kapphk.anotation.SystemLog)")    
     public  void controllerAspect() {    
     }    
    
    /**  
     * 前置通知 用于拦截Controller层记录用户的操作  
     *  
     * @param joinPoint 切点  
     */    
     @Before("controllerAspect()")    
     public  void doBefore(JoinPoint joinPoint) {    
    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        //读取session中的用户    
        SystemUser user = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
        //请求的IP    
        String ip = Common.getIpAddress(request);  
         try {    
            //*========数据库日志=========*//    
            com.kapphk.system.bean.SysLog log = new com.kapphk.system.bean.SysLog();
           // log.setDescription(getControllerMethodDescription(joinPoint));
           // log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setIpAddress(ip);    
            log.setSid(user.getId());
            log.setUserName(user.getUserName());
            log.setCreateTime(new Date());   
            //log.setUrl(request.getRequestURL().toString());
            //保存数据库    
            logService.insert(log);    
        }  catch (Exception e) {    
            //记录本地异常日志    
            logger.error("异常信息:{}", e.getMessage());    
        }    
    }    
    
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     @SuppressWarnings("rawtypes")
	public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemLog. class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }    
}    