package com.ur.java.demo.spring.conpool;

import lombok.Getter;

@Getter
public class DbConfig {
    private String driverName = "com.mysql.cj.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/lndemo?serverTimezone=Asia/Shanghai";

    private String username = "root";

    private String password = "123!@#qaz";
    /**
     * 最小空闲连接数
     */
    private int minFreeConnections = 1;
    /**
     * 最大空闲连接数
     */
    private int maxFreeConnections = 5;
    /**
     * 初始空闲连接数
     */
    private int initFreeConnections = 3;
    /**
     * 最大活跃连接数
     */
    private int maxActiveConnections = 10;

    private long reconnectTime = 1000;

    private long connectionTimeOut = 1000 * 60 * 20;
}
