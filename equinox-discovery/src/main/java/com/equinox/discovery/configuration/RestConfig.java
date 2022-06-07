package com.equinox.discovery.configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author boreas
 * @create 2022-04-05 16:29
 */
@Configuration
public class RestConfig {

    @Bean
    public IRule setRule(){
        // 默认轮询：ZoneAvoidanceRule()，复合判断Server所在区域的性能和Server的可用性选 择服务器 //可以设置随机调用 RandomRule()
        return new ZoneAvoidanceRule();
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
