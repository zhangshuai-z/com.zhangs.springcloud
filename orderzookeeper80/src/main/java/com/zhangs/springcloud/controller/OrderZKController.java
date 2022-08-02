package com.zhangs.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderZKController {

    public static final String INVOKE_URL = "http://cloud-provider-payment/";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("zk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "payment/zk", String.class);
    }
}
