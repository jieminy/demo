package com.ur.java.demo.thread.future;

public class FutureData<T> implements Data{
    private Boolean flag = false;
    private T data;


    synchronized void setData(T realData){
            if(!flag){
                data = realData;
                flag = true;
                notify();
            }
    }

    @Override
    public synchronized T getData() {
            if(!flag){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        return data;
    }

    public boolean isDone(){
        return flag;
    }
}
