package com.ur.java.demo.designer.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class JdkInvocationHandler implements InvocationHandler {
    private Object target;

    public JdkInvocationHandler(Object obj){
        this.target = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("动态代理-开启事务。。。");
        Object invoke = method.invoke(target, args);
        log.info("动态代理-关闭事务。。。");
        return invoke;
    }

    public static void main(String [] args){
        IUserDao userDao = new UserDaoImpl();
        JdkInvocationHandler invocationHandler = new JdkInvocationHandler(userDao);

        //类加载器
        ClassLoader classLoader = userDao.getClass().getClassLoader();
        //对象实现的所有接口
        Class<?>[] interfaces = userDao.getClass().getInterfaces();

        IUserDao iUserDao = (IUserDao) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        iUserDao.add();
    }
}
