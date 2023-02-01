package com.equinox.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 库存服务
 * @author boreas
 * @create 2022-08-18 21:24
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}

