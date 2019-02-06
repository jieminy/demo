package com.ur.java.demo.thread.future;

public class FutureRunnable<T> extends FutureData<T> implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("子线程并发处理任务>>>");
            T result = (T)"success";
            setData(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
