package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 19:49
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UploadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadServiceApplication.class);
    }

}
