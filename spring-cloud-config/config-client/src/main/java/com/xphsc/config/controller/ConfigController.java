package com.xphsc.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@RefreshScope
@RestController
public class ConfigController     {

    @Value("${config.client}")
    private String client;

    @GetMapping("/getClient")
    public Object getClient(){
        return  client;
    }
}
