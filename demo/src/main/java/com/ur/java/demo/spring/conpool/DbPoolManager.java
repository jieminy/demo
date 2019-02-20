package com.ur.java.demo.spring.conpool;

import java.sql.Connection;

public class DbPoolManager {
    DbConfig dbConfig = new DbConfig();
    DbPool dbPool = new DbPool(dbConfig);

    public Connection getConnection() {
        return dbPool.getConnection();
    }

    public void releaseConnection(Connection connection) {
        dbPool.releaseConnection(connection);
    }
}
