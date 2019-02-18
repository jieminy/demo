package com.ur.java.demo.spring.mvc;

import java.lang.reflect.Method;

public class UrlHandlerSupport {
    private Object target;
    private Method method;

    public UrlHandlerSupport(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public Object getTarget() {
        return target;
    }
}
