package com.zhangs.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/8/6 16:58
 * @author: zhangs
 */
public class SentinelHandlersException {
    @SentinelResource(
            value = "order", //代表Sentinel中资源名称
            blockHandler = "handleException", //限流处理方法
            blockHandlerClass = SentinelHandlersException.class, //限流处理类
            fallback = "handleError", //异常调用方法
            fallbackClass = SentinelHandlersException.class //异常处理类
    )
    //限流处理
    public static String handleException(BlockException blockException) {
        return "限流了！";
    }

    //异常处理
    public static String handleError(Throwable blockException) {
        return "限流出现异常！";
    }
}
