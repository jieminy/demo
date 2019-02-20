package com.ur.java.demo.spring.conpool;

import java.sql.Connection;

public class PoolMain {
    public static void main(String[] args) {
        DbPoolManager dbPoolManager = new DbPoolManager();
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new MyThread(dbPoolManager));
            thread.start();
        }
    }


}

class MyThread implements Runnable {
    private DbPoolManager dbPoolManager;

    public MyThread(DbPoolManager dbPoolManager) {
        this.dbPoolManager = dbPoolManager;
    }

    @Override
    public void run() {
        Connection connection = dbPoolManager.getConnection();
        System.out.println("线程：" + Thread.currentThread().getName() + "获取连接：" + connection);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dbPoolManager.releaseConnection(connection);
    }

}