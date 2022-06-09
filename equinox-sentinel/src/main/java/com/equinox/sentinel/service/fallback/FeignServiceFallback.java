package com.equinox.sentinel.service.fallback;

import com.equinox.sentinel.service.ItemShardingFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 使用FeignClient+fallback进行服务降级
 */
@Service
@Slf4j
public class FeignServiceFallback implements ItemShardingFeignService {

    @Override
    public String list() {
        log.error("当前访问人数过多，请稍后再试！");
        return "当前访问人数过多，请稍后再试！";
    }

    @Override
    public String rt() {
        log.error("rt接口-默认熔断");
        return "rt接口-默认熔断";
    }

    @Override
    public String flow() {
        log.error("flow接口-默认熔断");
        return "flow接口-默认熔断";
    }

    @Override
    public String error() {
        log.error("error接口-默认熔断");
        return "error接口-默认熔断";
    }

    @Override
    public String thread() {
        log.error("thread接口-默认熔断");
        return "thread接口-默认熔断";
    }
}
