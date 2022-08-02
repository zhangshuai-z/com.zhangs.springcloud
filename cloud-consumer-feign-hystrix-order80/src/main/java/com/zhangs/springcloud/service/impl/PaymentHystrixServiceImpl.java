package com.zhangs.springcloud.service.impl;

import com.zhangs.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/8/1 1:45
 * @author: zhangs
 */
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back-paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----PaymentFallbackService fall back-paymentInfo_TimeOut";
    }
}
