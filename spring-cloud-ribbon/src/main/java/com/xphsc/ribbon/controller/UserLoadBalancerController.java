package com.xphsc.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@RestController
public class UserLoadBalancerController {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/getloadBalancer")
    public Object getloadBalancer() {
        ServiceInstance instance = loadBalancer.choose("user-server");
        URI userUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
        // 打印当前选择的是哪点节点
        System.out.println("打印当前选择的是哪点节点:{}"+userUri);
        // ... do something with the URI
        return userUri;
    }

}
