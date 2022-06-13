package com.equinox.actuator.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public CustomerMetricsIndicator customerMetricsIndicator(){
        return new CustomerMetricsIndicator();
    }
}
