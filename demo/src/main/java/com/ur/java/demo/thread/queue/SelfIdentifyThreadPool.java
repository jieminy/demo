package com.ur.java.demo.thread.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelfIdentifyThreadPool {
    public static void main(String[] args){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));
        threadPoolExecutor.execute(new MyThread("1"));
        threadPoolExecutor.execute(new MyThread("2"));
        threadPoolExecutor.execute(new MyThread("3"));
        threadPoolExecutor.execute(new MyThread("4"));
        threadPoolExecutor.execute(new MyThread("5"));
//        threadPoolExecutor.execute(new MyThread("6"));
        threadPoolExecutor.shutdown();
    }
}

class MyThread implements Runnable{
    private String name;
    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+":" + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}