package com.ur.java.demo.jvm.paramsetting;

/**
 * -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:NewRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * SurvivorRatio eden : from/to = 2
 */
public class SurvivorJvmMain {
    public static void main(String[] args) {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }
}
