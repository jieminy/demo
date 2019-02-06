package com.ur.java.demo.thread.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MyAtomicThread implements Runnable{
    private AtomicInteger count = new AtomicInteger(0);
    @Override
    public void run() {
        while(count.getAndIncrement() < 150){
            log.info("当前count:" + count.get());
        }
    }
}