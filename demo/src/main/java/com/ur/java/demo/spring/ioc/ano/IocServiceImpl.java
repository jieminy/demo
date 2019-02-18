package com.ur.java.demo.spring.ioc.ano;

@UrService
public class IocServiceImpl {
    @UrAutowired
    private AnoServiceImpl anoService;

    public void ico() {
        System.out.println("测试DI注入 >>>");
        anoService.ano();
    }
}
