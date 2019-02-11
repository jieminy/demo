package com.ur.java.demo.jvm.paramsetting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * -Xss1m
 * 栈溢出异常
 */
public class StackOverFlowMain {

    public static int count;

    public static void count() {
        try {
            count++;
            count();
        } catch (Throwable e) {
            log.info("最大深度：" + count);
        }

    }

    public static void main(String[] args) {
        count();
    }
}
