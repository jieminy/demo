package com.ur.java.demo.spring.annotation;

public class AntService {
    @MyAnnotation(username = "lisi", arrays = {"aaaa"})
    public void add() {
    }
}
