package com.ur.java.demo.thread.future;

import java.util.concurrent.Callable;

public class FutureClient<T> {


    public FutureData<T> submit(Callable<T> callable){
        FutureData<T> futureData = new FutureData<>();
        Thread thread = new Thread(() -> {
            try {
                T data = callable.call();
                futureData.setData(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        return futureData;
    }
}


