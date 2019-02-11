package com.ur.java.demo.jvm.classloader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class HotClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //1.读取文件名
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            //2.读取文件流
            InputStream is = this.getClass().getResourceAsStream(fileName);
            //3.读取子节
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            //4.将读取的byte数组给jvm识别class对象
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            log.error("类加载失败", e);
            throw new RuntimeException("类加载失败！");
        }
    }


}