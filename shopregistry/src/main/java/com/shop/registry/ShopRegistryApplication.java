package com.shop.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 13:01
 */
@EnableEurekaServer
@SpringBootApplication
public class ShopRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopRegistryApplication.class);
    }
}
