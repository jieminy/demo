package com.ur.java.demo.jvm.paramsetting;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseSerialGC
 * 内存溢出
 */
public class OutOfMemoryMain {
    public static void main(String[] args) {
        Byte[] b = new Byte[10 * 1024 * 1024];
    }
}
