package com.xphsc.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class FeignApplication {
    public static void main(String[] args) {
    SpringApplication.run(FeignApplication.class, args);
}

}
