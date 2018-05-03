package com.xphsc.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClientApplication.class).run(args);
    }
}
