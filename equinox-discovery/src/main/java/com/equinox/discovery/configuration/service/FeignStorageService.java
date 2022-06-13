package com.equinox.discovery.configuration.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author boreas
 * @create 2022-03-28 23:30
 */
//@RequestMapping("/")
//@FeignClient(value = "service-storage")
public interface FeignStorageService {

    @RequestMapping(value = "/storage")
    String storage();
}
