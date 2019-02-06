package com.ur.java.demo.thread.singleton;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class ProtectionFromReflection {
    private static final ProtectionFromReflection instance = new ProtectionFromReflection();
    private static boolean flag = false;

    private ProtectionFromReflection(){
        if(!flag){
            flag = !flag;
            log.info("成功创建ProtectionFromReflection");
        }else{
            log.error("不可重复创建ProtectionFromReflection");
            throw new RuntimeException("不可重复创建ProtectionFromReflection");
        }
        log.info("获取实例对象" + flag);
    }

    public static ProtectionFromReflection getInstance(){
        return instance;
    }
    public static void main(String [] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ProtectionFromReflection protection = ProtectionFromReflection.getInstance();

        Class<?> forname = Class.forName("com.example.demo.singleton.ProtectionFromReflection");
        Constructor<?> constructor = forname.getDeclaredConstructor();
        constructor.setAccessible(true);
        ProtectionFromReflection obj = (ProtectionFromReflection)constructor.newInstance();

        log.info("俩个对象时否相同" + protection);
        log.info("俩个对象时否相同" + obj);
    }

}
