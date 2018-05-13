package com.xphsc.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@SpringBootApplication
@EnableEurekaClient
public class ZipkinConsumerApplication {
    public static void main(String[] args) {
    SpringApplication.run(ZipkinConsumerApplication.class, args);
}

}
