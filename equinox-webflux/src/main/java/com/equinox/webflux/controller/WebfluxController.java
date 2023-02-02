package com.equinox.webflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;

/**
 * @author boreas
 * @create 2023-02-01 15:29
 */
@RequestMapping("/webflux")
@RestController
public class WebfluxController {

    @Resource
    private WebClient webClient;

    // Mono 实现了 org.reactivestreams.Publisher 接口，代表0到1个元素的发布者。
    @RequestMapping("/mono")
    public Mono<String> mono() {
        return Mono.just("hello webflux");
    }

    // Flux 同样实现了 org.reactivestreams.Publisher 接口，代表0到N个元素的发表者。
    @CrossOrigin
    @RequestMapping("/flux")
    public Flux<ServerSentEvent<String>> flux() {
        // 使用interval创建间隔某一时间异步执行的Flux
        return Flux.interval(Duration.ofMillis(1000))
                .map(val -> {
                    return ServerSentEvent.<String>builder()
                            .id(UUID.randomUUID().toString())
                            .event("test_event")
                            .data(val.toString())
                            .build();
                });
    }

    @RequestMapping("/listFlux")
    public Flux<Map<String, String>> listFlux() {
        List<Map<String, String>> list = new ArrayList<>();
        list.add(new HashMap<>());
        // range(start,count) 类似于我们的分页，从1开始
        return Flux.range(1, 3)
                .map(val -> {// val 可以作为下标使用，从0开始
                    return list.stream()
                            .filter(map -> map.get("id").equals(val))
                            .findFirst()// findFirst()拿到第一个匹配
                            .get();
                }).delayElements(Duration.ofMillis(1000));
    }

    @RequestMapping("/request")
    public Mono<String> request(@RequestParam("id") Integer id, String name, ServerHttpRequest request) {
        return Mono.just(request.getURI().toString());
    }

    @RequestMapping("/restFul/{id}/{name}")
    public Mono<String> restFul(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return webClient.get()
                .uri("https://cdn.jin10.com/trading-clock/new/data.json", id)
                // retrieve()方法可用于声明如何提取响应，将响应转为ResponseEntity。也可以用来提取body体，将body内容转为指定类型的对象
                .retrieve()
                // 4xx或5xx响应会导致WebClientResponseException，包括特定HTTP状态代码的子类。若要自定义错误响应的处理，使用onStatus处理
                .onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new RuntimeException(clientResponse.statusCode().value() + " error code"));
                })
                .bodyToMono(String.class);
    }

    /*以下两个方法是URI传参方式*/
    @RequestMapping("requestParam")
    public Mono<String> requestParam(@RequestParam("id") Integer id , String name){
        return WebClient
                .create("http://localhost:8080/request")
                .get()
                .uri(uriBuilder -> uriBuilder.path("/request")
                        .queryParam("id",id)
                        .queryParam("name",name).build())
                .retrieve()
                .bodyToMono(String.class);
    }
    @RequestMapping("restFul2/{id}/{name}")
    public Mono<String> restFul2(@PathVariable("id") Integer id , @PathVariable("name") String name){
        return WebClient
                .create("http://localhost:8080/restful/")
                .get()
                .uri(uriBuilder -> uriBuilder.path("/{id}/{name}").build(id,name))
                .retrieve()
                .onStatus(HttpStatus::isError,clientResponse -> {
                    return Mono.error(new RuntimeException(clientResponse.statusCode().value() +" error code"));
                })
                .bodyToMono(String.class);
    }
}
