package com.equinox.autoai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author boreas
 * @create 2023-07-03 11:01
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AutoAiApplication {
    public static void main(final String[] args) {
        SpringApplication.run(AutoAiApplication.class, args);
    }
}









