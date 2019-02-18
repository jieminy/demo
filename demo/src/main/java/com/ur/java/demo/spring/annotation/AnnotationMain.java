package com.ur.java.demo.spring.annotation;

import java.lang.reflect.Method;

public class AnnotationMain {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.ur.java.demo.spring.annotation.AntService");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                System.out.println("username:" + annotation.username());
                for (String ar : annotation.arrays()) {
                    System.out.println("item:" + ar);
                }
            }
        }
    }

}
