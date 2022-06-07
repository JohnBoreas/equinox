package com.equinox.discovery.controller;

import com.equinox.discovery.service.FeignStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/discovery")
public class ServerDiscoveryController {

    RestTemplate restTemplate;

//    FeignStorageService feignStorageService;

    @Autowired
    public ServerDiscoveryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find() {
        String result = restTemplate.getForEntity("http://service-storage/storage", String.class).getBody();
        System.out.println("扣减库存结果：" + result);
//
//        String result1 = feignStorageService.storage();
//        System.out.println("扣减库存结果：" + result1);
        return "order success";
    }

}
