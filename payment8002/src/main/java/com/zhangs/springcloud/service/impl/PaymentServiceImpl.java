package com.zhangs.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangs.springcloud.entity.Payment;
import com.zhangs.springcloud.mapper.PaymentMapper;
import com.zhangs.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        int result = paymentMapper.create(payment);
        return result;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment paymentById = paymentMapper.getPaymentById(id);
        return paymentById;
    }
    public int deletePaymentById(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        int delete = baseMapper.delete(wrapper);
        return delete;
    }
}
