package com.zhangs.springcloud.service;

import com.zhangs.springcloud.entity.CommonResult;
import com.zhangs.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/7/31 21:00
 * @author: zhangs
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @PostMapping("payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @GetMapping("payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimeout();
}