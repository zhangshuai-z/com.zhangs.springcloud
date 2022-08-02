package com.zhangs.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/7/31 19:39
 * @author: zhangs
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
