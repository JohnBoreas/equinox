package com.equinox.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author boreas
 * @create 2022-03-22 21:17
 */
//需要排除springboot中德鲁伊默认自动配置，否则会出现bean定义覆盖问题
@SpringBootApplication()
public class EquinoxWebfluxApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EquinoxWebfluxApplication.class, args);
    }
}
