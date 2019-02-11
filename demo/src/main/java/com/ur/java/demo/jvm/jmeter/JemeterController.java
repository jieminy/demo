package com.ur.java.demo.jvm.jmeter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
/**
 *
 * 串行收集器：-XX:+PrintGCDetails -Xmx512m -Xms512m -XX:+UseSerialGC   吞吐量 3563
 * 并行收集器：-XX:+PrintGCDetails -Xmx512m -Xms512m -XX:+UseParNewGC  吞吐量 3448
 */
public class JemeterController {
    private AtomicInteger count = new AtomicInteger(0);

    @GetMapping("index")
    public String index() {
        String result = "" + count.getAndIncrement();
//        log.info(result);
        return result;
    }
}
