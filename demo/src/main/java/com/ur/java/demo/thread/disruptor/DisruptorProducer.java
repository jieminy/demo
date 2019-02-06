package com.ur.java.demo.thread.disruptor;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public class DisruptorProducer   {

    private RingBuffer<LongEvent> ringBuffer;

    public DisruptorProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){
        Long sequence = ringBuffer.next();
        try{
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.setValue(byteBuffer.getLong(0));
            log.info("生产者生产数据：" + longEvent.getValue());
        }catch(Exception e){
            log.error("生产数据失败", e);
        }finally {
            ringBuffer.publish(sequence);
        }

    }
}
