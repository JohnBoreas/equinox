package com.equinox.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author boreas
 * @create 2022-03-22 21:17
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class SentinelApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }
}
