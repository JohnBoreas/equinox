package com.equinox.webflux.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

/**
 * @author boreas
 * @create 2023-02-01 15:29
 */
@RequestMapping("/webflux")
@RestController
public class WebfluxController {

    @RequestMapping("/mono")
    public Mono<String> mono() {
        return Mono.just("hello webflux");
    }

    @CrossOrigin
    @RequestMapping("/flux")
    public Flux<ServerSentEvent<String>> flux(){
        return Flux.interval(Duration.ofMillis(1000)).map(val -> {
            return ServerSentEvent.<String>builder()
                    .id(UUID.randomUUID().toString())
                    .event("test_event")
                    .data(val.toString())
                    .build();
        });
    }
}
