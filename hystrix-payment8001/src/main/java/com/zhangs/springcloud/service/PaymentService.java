package com.zhangs.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/7/31 23:46
 * @author: zhangs
 */
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "paymentInfo_OK,id: " + "\t" + "o(n_n)哈哈~";
    }

    //设置3s以内属于正常的业务逻辑,超过3s属于异常,需要执行fallbackMethod
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
//        int timeNumber = 5;
        int ag = 10 / 0;
//        try {
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return "线程池: " + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: " + "\t" + "o(n_n)哈哈~" + "耗时"  + "秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池: " + Thread.currentThread().getName() + "系统繁忙或运行报错，请稍后再试,id: " + "\t" + "/(ㄒoㄒ)/~~";
    }

    //==========================服务熔断===============================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id <= 0) {
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试,id=" + id;
    }

}
