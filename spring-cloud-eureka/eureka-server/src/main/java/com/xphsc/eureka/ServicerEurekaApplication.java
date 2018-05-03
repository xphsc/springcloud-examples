package com.xphsc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@SpringBootApplication
@EnableEurekaServer
public class ServicerEurekaApplication {
    public static void main(String[] args) {
    SpringApplication.run(ServicerEurekaApplication.class, args);
}

}
