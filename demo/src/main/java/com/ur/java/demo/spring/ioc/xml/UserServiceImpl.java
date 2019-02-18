package com.ur.java.demo.spring.ioc.xml;

public class UserServiceImpl {
    private String cn;

    public void add() {
        System.out.println("添加用户");
    }

    public void init() {
        System.out.println("我是初始化方法");
    }
}
