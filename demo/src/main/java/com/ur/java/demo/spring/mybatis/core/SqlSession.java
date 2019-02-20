package com.ur.java.demo.spring.mybatis.core;

import java.lang.reflect.Proxy;

public class SqlSession {
    public static <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MybatisInvocationHandler());
    }
}
