package com.equinox.webflux;

import reactor.core.publisher.Mono;

/**
 * @author boreas
 * @create 2023-02-09 17:04
 */
public class TestMonoMain {

    public static void main(String[] args) {
        Mono.just("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy").subscribe(System.out::println);
    }
}
