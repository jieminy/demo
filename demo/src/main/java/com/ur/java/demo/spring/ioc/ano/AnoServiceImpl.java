package com.ur.java.demo.spring.ioc.ano;

@UrService
public class AnoServiceImpl {
    public void ano() {
        System.out.println("注入anoService执行业务逻辑");
    }
}
