package com.ur.java.demo.thread.future;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FutureMain {
    public static void main(String[] args){
        FutureClient<String> futureClient = new FutureClient<>();
        log.info("主程序>>>好累呀，我需要子线程帮我分担一点事情");
        FutureData<String> futureData = futureClient.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "子线程>>>拼命干活中");
            Thread.sleep(5000);
            return "1000万预算";
        });

        log.info("主线程>>>继续专注自己的工作");
        log.info("主线程>>>让我看看子线程有没有把事情办好");
        int i = 0;
        while(!futureData.isDone()){
            log.info("主线程>>>子线程还没有完事，等它一会吧");
            try {
                Thread.sleep(1000);
                log.info(++ i+ "秒钟过后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("主线程>>>子线程总算帮我把事情办完了，帮我省了好多事，唔：" + futureData.getData());
    }
}
