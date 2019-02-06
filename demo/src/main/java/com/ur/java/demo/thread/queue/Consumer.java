package com.ur.java.demo.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private volatile boolean flag;
    private AtomicInteger count;

    public Consumer(BlockingQueue blockingQueue, boolean flag, AtomicInteger count){
        this.blockingQueue = blockingQueue;
        this.flag = flag;
        this.count = count;
    }

    @Override
    public void run() {
        while (flag){
            String data = count.getAndIncrement() + "";
            boolean isAdded = blockingQueue.offer(data);
            if(isAdded){
                System.out.println("生产数据成功");
            }else{
                System.out.println("生产数据失败");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
