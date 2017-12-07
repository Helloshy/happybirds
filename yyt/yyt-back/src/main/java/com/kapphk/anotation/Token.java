package com.kapphk.anotation;

import java.lang.annotation.*;

/** 
*
* @author EXIA
* @version 1.0
* @createDate 2016年9月26日 下午5:53:51 
*
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    boolean save() default false;

    boolean remove() default false;
}