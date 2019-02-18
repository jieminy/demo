package com.ur.java.demo.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//目标作用域
@Target(value = ElementType.METHOD)
//用于描述注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String username() default "张三";

    String[] arrays();

}
