package com.ur.java.demo.spring.ioc.ano;

public class AnoMain {
    public static void main(String[] args) {
        UrAnnotationApplicationContext context = new UrAnnotationApplicationContext("com.ur.java.demo.spring.ioc.ano");
//        AnoServiceImpl anoService = (AnoServiceImpl) context.getBean("anoServiceImpl");
//        anoService.ano();
        IocServiceImpl iocService = (IocServiceImpl) context.getBean("iocServiceImpl");
        iocService.ico();
    }
}
