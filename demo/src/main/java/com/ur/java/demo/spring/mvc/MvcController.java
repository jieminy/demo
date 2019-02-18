package com.ur.java.demo.spring.mvc;

import lombok.extern.slf4j.Slf4j;

@UrController
@UrRequestMapping("/mvc")
@Slf4j
public class MvcController {

    @UrRequestMapping("/test")
    public String mvc() {
        log.info("调用/mvc/test");
        return "hello";
    }
}
