package com.equinox.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author boreas
 * @create 2022-04-05 16:28
 */
// @EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder().profiles("zuul")
                .sources(ZuulGatewayApplication.class)
                .run(args);
    }
}
