package com.xphsc.ribbon.controller;

import com.xphsc.ribbon.config.RibbonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/queryString")
    public String queryString(){
       return restTemplate.getForObject("http://user-server/queryString",String.class);
    }

}
