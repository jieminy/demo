package com.ur.java.demo.jvm.paramsetting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * -Xmx20m -Xms5m
 * @param args
 */
public class JvmMain {
    public static void main(String[] args) {
        byte[] b = new byte[4 * 1024 * 1024];
        log.info("分配4M内存给数组");

        log.info("最大内存：" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
        log.info("可用内存：" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        log.info("已用内存：" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}
