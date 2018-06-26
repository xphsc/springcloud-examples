package com.xphsc.refresh;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigRefreshClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigRefreshClientApplication.class).run(args);
    }
}
