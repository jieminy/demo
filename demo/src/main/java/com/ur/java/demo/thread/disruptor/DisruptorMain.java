package com.ur.java.demo.thread.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DisruptorMain {
    public static void main(String[] args){
        // 1.创建线程池
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        // 2.创建Event工厂
        FloatEventFactory factory = new FloatEventFactory();
        // 3.创建RingBuffer的大小
        int rbSize = 1024 * 1024;
        // 4.创建Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, rbSize, threadFactory, ProducerType.MULTI, new YieldingWaitStrategy());
        // 5.注册消费者
        disruptor.handleEventsWith(new DisruptorConsumer());
        // 6.启动
        disruptor.start();
        // 7.创建RingBuffer容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 8.创建生产者
        DisruptorProducer producer = new DisruptorProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for(int i=0; i<100; i++){
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
        }
        disruptor.shutdown();

    }
}
