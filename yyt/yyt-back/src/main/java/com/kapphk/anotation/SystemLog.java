package com.kapphk.anotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author EXIA
 *
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public @interface SystemLog {
	String description()  default "";  
}
