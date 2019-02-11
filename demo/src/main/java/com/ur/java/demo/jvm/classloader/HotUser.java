package com.ur.java.demo.jvm.classloader;

public class HotUser {
    public HotUser() {
        System.out.println("User.class 版本V1.0");
    }

    public void add() {
        System.out.println("添加用户 old");
    }


}
