package com.ur.java.demo.spring.conpool;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DbPool {
    private Vector<Connection> freeConnection;
    private Vector<Connection> activeConnection;
    private DbConfig config;
    private AtomicInteger currentCount = new AtomicInteger(0);

    public DbPool(DbConfig config) {
        this.config = config;
        init();
    }

    private void init() {
        if (config == null) {
            throw new RuntimeException("获取配置文件失败");
        }
        freeConnection = new Vector<>(config.getMaxFreeConnections());
        activeConnection = new Vector<>(config.getMaxActiveConnections());

        for (int i = 0; i < config.getInitFreeConnections(); i++) {
            freeConnection.add(newConnection());
        }
    }

    private Connection newConnection() {
        try {
            Class.forName(config.getDriverName());
            return DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("创建数据库连接失败");
        }
    }


    //获取连接
    public synchronized Connection getConnection() {
        try {
            if (currentCount.get() < config.getMaxActiveConnections()) {
                Connection connection = null;
                if (freeConnection.size() > 0) {
                    log.info("空闲池有连接，取出连接");
                    connection = freeConnection.remove(0);
                } else {
                    log.info("空闲池无连接，创建新连接");
                    connection = newConnection();
                }
                //判断连接是否可用
                if (isAvailable(connection)) {
                    currentCount.getAndIncrement();
                    activeConnection.add(connection);
                } else {
                    getConnection();
                }
                return connection;
            } else {
                log.info("连接数已满，等待连接释放");
                wait(config.getReconnectTime());
                return getConnection();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("获取连接失败");
        }
    }

    /**
     * 释放连接
     *
     * @param connection 连接
     */
    public synchronized void releaseConnection(Connection connection) {
        if (isAvailable(connection)) {
            if (freeConnection.size() < config.getMaxFreeConnections()) {
                activeConnection.remove(connection);
                freeConnection.add(connection);
                log.info("释放连接，返还空闲池");
                currentCount.decrementAndGet();
                notifyAll();
            } else {
                try {
                    log.info("关闭连接");
                    connection.close();
                    currentCount.decrementAndGet();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("释放连接失败");
                }
            }
        }
    }

    private boolean isAvailable(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
