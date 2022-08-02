package com.zhangs.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/7/31 23:17
 * @author: zhangs
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
