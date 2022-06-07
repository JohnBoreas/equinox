package com.equinox.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 库存服务
 * @author boreas
 * @create 2022-04-05 21:24
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}

