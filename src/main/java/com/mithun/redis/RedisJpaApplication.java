package com.mithun.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisJpaApplication.class, args);
    }
}
