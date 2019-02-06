package com.ur.java.demo.designer.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class CglibCallback implements MethodInterceptor {
    private Object target;
    public Object getProxy(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("cglib动态代理-开启事务...");
        Object invoke = methodProxy.invoke(target, objects);
        log.info("cglib动态代理-关闭事务...");
        return invoke;
    }

    public static void main(String [] args){
        UserDaoImpl userDao = new UserDaoImpl();
        CglibCallback callback = new CglibCallback();
        UserDaoImpl proxy = (UserDaoImpl) callback.getProxy(userDao);
        proxy.add();
    }

}
