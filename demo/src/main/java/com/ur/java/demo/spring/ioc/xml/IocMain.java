package com.ur.java.demo.spring.ioc.xml;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class IocMain {

    public static void main(String[] args) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        userService.add();
    }


}
