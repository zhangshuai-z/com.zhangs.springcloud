package com.zhangs.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Server7002Main {
    public static void main(String[] args) {
        SpringApplication.run(Server7002Main.class, args);
    }
}
