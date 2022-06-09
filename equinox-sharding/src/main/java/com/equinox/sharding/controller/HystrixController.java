package com.equinox.sharding.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boreas
 * @create 2022-06-09 13:30
 */
@Slf4j
@RequestMapping(value = "/hystrix")
@RestController
public class HystrixController {


    @GetMapping("/rt")
    public String rt() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            // ignore
        }
        return "rt：sleep 1s";
    }

    @GetMapping("/flow")
    public String flow() {
        return "flow";
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("custom");
    }

    @GetMapping("/thread")
    public String thread() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            // ignore
        }
        return "thread";
    }
}
