package com.zhangs.springcloud.controller;

import com.zhangs.springcloud.entity.CommonResult;
import com.zhangs.springcloud.entity.Payment;
import com.zhangs.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***插入结果: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult(201, "插入数据失败",null);
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("***查询结果: " + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "根据id查询数据成功,serverPort: " + serverPort, paymentById);
        } else {
            return new CommonResult(201, "根据id查询数据成功失败",null);
        }
    }

    @GetMapping("discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("******element: " + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return serverPort;
    }
}
