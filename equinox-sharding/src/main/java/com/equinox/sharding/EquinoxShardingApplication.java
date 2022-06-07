package com.equinox.sharding;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author boreas
 * @create 2022-03-22 21:17
 */
@EnableDiscoveryClient
@SpringBootApplication
//@EnableFeignClients
public class EquinoxShardingApplication {

    public static void main(final String[] args) {
        new SpringApplicationBuilder().profiles("nacos")
                .sources(EquinoxShardingApplication.class)
                .run(args);
    }
}
