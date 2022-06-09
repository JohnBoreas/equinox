package com.equinox.sentinel.service;

import com.equinox.sentinel.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "equinox-sharding", fallbackFactory = DefaultFallbackFactory.class)
public interface ItemShardingFeignService {

    @RequestMapping("/item/list")
    String list();

    @GetMapping("/hystrix/rt")
    String rt();

    @GetMapping("/hystrix/flow")
    String flow();

    @GetMapping("/hystrix/error")
    String error();

    @GetMapping("/hystrix/thread")
    String thread();
}
