package com.zhangs.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhangs.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/8/6 15:57
 * @author: zhangs
 */
@Slf4j
@RestController
@RequestMapping("/sentinel")
public class FlowLimitController {

    @Resource
    private OrderService orderService;

    @GetMapping("/testA")
    public String testA() {
        String order = orderService.getOrder();

        return order + "---" + "..testA";
    }

    @GetMapping("/testB")
    public String testB() {
        String order = orderService.getOrder();

        return order + "---" + "..testB";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String order = orderService.getOrder();
        log.info("testD 测试RT");
        return order + "---" + "..testD";
    }

    @GetMapping("/testE")
    public String testE() {

        String order = orderService.getOrder();
        log.info("testD 测试异常比例");
        int age = 10/0;
        return order + "---" + "..testE";
    }

    @GetMapping("/testF")
    public String testF() {

        String order = orderService.getOrder();
        log.info("testF 测试异常数");
        int age = 10/0;
        return order + "---" + "..testF";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false)String p1,
                             @RequestParam(value = "p2", required = false)String p2) {

        String order = orderService.getOrder();
        return order + "---" + "..testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {

        String order = orderService.getOrder();
        return order + "---" + "..deal_testHotKey";
    }
}
