package com.equinox.sentinel.service.fallback;

import com.equinox.sentinel.service.ItemShardingFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class SentinelFallback implements ItemShardingFeignService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public String list() {
        String msg = "storage接口-Sentinel熔断---" + sdf.format(new Date());
        log.error(msg);
        return msg;
    }

    @Override
    public String rt() {
        String msg = "rt接口-Sentinel熔断---" + sdf.format(new Date());
        log.error(msg);
        return msg;
    }

    @Override
    public String flow() {
        String msg = "flow接口-Sentinel熔断---" + sdf.format(new Date());
        log.error(msg);
        return msg;
    }

    @Override
    public String error() {
        String msg = "error接口-Sentinel熔断---" + sdf.format(new Date());
        log.error(msg);
        return msg;
    }

    @Override
    public String thread() {
        String msg = "thread接口-Sentinel熔断---" + sdf.format(new Date());
        log.error(msg);
        return msg;
    }
}
