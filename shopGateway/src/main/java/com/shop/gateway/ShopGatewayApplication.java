package com.shop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 14:22
 */
@EnableZuulProxy
@SpringCloudApplication
public class ShopGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopGatewayApplication.class);
    }
}
