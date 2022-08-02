package com.zhangs.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhangs
 */
@RestController
@RequestMapping("order")
public class OrderConsulController {

    public static final String INVOKE_URL = "http://consul-provider-payment/";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "payment/consul", String.class);
    }
}
