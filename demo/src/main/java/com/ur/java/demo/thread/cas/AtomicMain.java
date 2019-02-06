package com.ur.java.demo.thread.cas;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicMain {

    public static void main(String[] args){
        MyAtomicThread atomicThread = new MyAtomicThread();
        Thread t1 = new Thread(atomicThread);
        Thread t2 = new Thread(atomicThread);
        t1.start();
        t2.start();
    }

}

