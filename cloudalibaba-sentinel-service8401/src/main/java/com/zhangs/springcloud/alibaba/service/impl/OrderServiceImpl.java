package com.zhangs.springcloud.alibaba.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zhangs.springcloud.alibaba.handler.SentinelHandlersException;
import com.zhangs.springcloud.alibaba.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/8/6 17:05
 * @author: zhangs
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    @SentinelResource(
            value = "order", //代表Sentinel中资源名称
            blockHandler = "handleException", //限流处理方法
            blockHandlerClass = SentinelHandlersException.class, //限流处理类
            fallback = "handleError", //异常调用方法
            fallbackClass = SentinelHandlersException.class //异常处理类
    )
    public String getOrder() {
        return String.valueOf(new Random().nextInt());
    }
}
