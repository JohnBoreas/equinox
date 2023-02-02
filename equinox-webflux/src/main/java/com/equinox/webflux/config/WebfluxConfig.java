package com.equinox.webflux.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.resources.LoopResources;

import java.util.concurrent.TimeUnit;

/**
 * @author boreas
 * @create 2023-02-02 16:00
 */
@Configuration
public class WebfluxConfig {

    @Bean("reactorResourceFactory")
    @Qualifier("reactorResourceFactory")
    public ReactorResourceFactory reactorResourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setLoopResources(LoopResources.create("reactor-http", 1500, true));
        factory.setUseGlobalResources(false);
        return factory;
    }

    @Bean("clientHttpConnector")
    @Qualifier("clientHttpConnector")
    public ClientHttpConnector clientHttpConnector(@Qualifier("reactorResourceFactory") ReactorResourceFactory reactorResourceFactory) {
        return new ReactorClientHttpConnector(reactorResourceFactory, mapper -> mapper
                .tcpConfiguration(tcpClient -> tcpClient
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1500)
                        .option(ChannelOption.SO_TIMEOUT, 1500)
                        .doOnConnected(conn -> {
                            conn.addHandlerLast(new ReadTimeoutHandler(1500, TimeUnit.MILLISECONDS));
                        })
                ).compress(true)
        );
    }

    @Bean("webClient")
    @Qualifier("webClient")
    public WebClient webClient(@Qualifier("clientHttpConnector") ClientHttpConnector clientHttpConnector) {
        return WebClient.builder()
                .clientConnector(clientHttpConnector).build();
    }
}
