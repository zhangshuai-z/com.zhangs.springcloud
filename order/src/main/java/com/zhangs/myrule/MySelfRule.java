package com.zhangs.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/7/31 16:09
 * @author: zhangs
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        //定义为随机
        return new RandomRule();
    }
}
