package com.equinox.sentinel.service.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultFallbackFactory implements FallbackFactory {

    private SentinelFallback sentinelFallback;

    private FeignServiceFallback defaultFallback;

    @Autowired
    public DefaultFallbackFactory(FeignServiceFallback defaultFallback,
                                  SentinelFallback sentinelFallback) {
        this.defaultFallback = defaultFallback;
        this.sentinelFallback = sentinelFallback;
    }

    @Override
    public Object create(Throwable cause) {
        if (cause instanceof BlockException) {
            return sentinelFallback;
        } else {
            log.info("error type " + cause.getMessage());
            return defaultFallback;
        }
    }
}
