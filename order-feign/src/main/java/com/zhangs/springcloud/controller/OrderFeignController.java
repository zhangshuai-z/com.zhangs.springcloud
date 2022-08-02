package com.zhangs.springcloud.controller;

import com.zhangs.springcloud.entity.CommonResult;
import com.zhangs.springcloud.entity.Payment;
import com.zhangs.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/7/31 21:05
 * @author: zhangs
 */
@Slf4j
@RestController
@RequestMapping("/feign")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        return paymentFeignService.create(payment);
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/timeout")
    public String paymentFeignTimeout() {
        //openfeign-ribbon,客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }

}
