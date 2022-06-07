package com.equinox.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author boreas
 * @create 2022-03-22 21:17
 */
// @EnableDiscoveryClient

//@EnableEurekaServer
@SpringBootApplication
public class EurekaDiscoveryApplication {

    public static void main(final String[] args) {
        new SpringApplicationBuilder().profiles("eureka")
                .sources(EurekaDiscoveryApplication.class)
                .run(args);
    }
}
