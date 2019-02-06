package com.ur.java.demo.thread.disruptor;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisruptorConsumer implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        log.info("消费者1消费数据" + longEvent.getValue());
    }
}
