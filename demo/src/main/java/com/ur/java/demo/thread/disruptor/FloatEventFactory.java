package com.ur.java.demo.thread.disruptor;

import com.lmax.disruptor.EventFactory;

public class FloatEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
