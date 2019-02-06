package com.ur.java.demo.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private volatile boolean flag;
    private AtomicInteger count;

    public Producer(BlockingQueue blockingQueue, boolean flag, AtomicInteger count){
        this.blockingQueue = blockingQueue;
        this.flag = flag;
        this.count = count;
    }

    @Override
    public void run() {
        while (flag){
            try {
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if(data == null){
                    System.out.println("消费者获取数据失败");
                    return;
                }
                System.out.println("消费者成功获取数据：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
