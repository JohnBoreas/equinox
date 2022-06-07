package com.equinox.sharding;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author boreas
 * @create 2022-03-22 21:17
 */
@EnableDiscoveryClient
//需要排除springboot中德鲁伊默认自动配置，否则会出现bean定义覆盖问题
@SpringBootApplication(exclude={DruidDataSourceAutoConfigure.class})
public class EquinoxShardingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EquinoxShardingApplication.class, args);
    }
}
