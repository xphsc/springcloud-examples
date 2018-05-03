package com.xphsc.ribbon.controller;

import com.xphsc.ribbon.config.RibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@RestController
@RibbonClient(name = "user-server", configuration = RibbonConfig.class)
public class UserController {


}
