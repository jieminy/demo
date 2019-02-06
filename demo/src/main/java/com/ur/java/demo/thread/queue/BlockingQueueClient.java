package com.ur.java.demo.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueClient {
    private static volatile boolean flag = true;
    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        Producer producer = new Producer(blockingQueue, flag, count);
        Consumer consumer = new Consumer(blockingQueue, flag, count);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();

    }
}
