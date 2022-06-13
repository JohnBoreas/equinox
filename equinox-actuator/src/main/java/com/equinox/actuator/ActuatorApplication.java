package com.equinox.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author boreas
 * @create 2022-03-22 21:17
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ActuatorApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }
}
