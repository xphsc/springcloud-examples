package com.xphsc.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;


/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinStreamServer
public class ZipkinApplication {
    public static void main(String[] args) {
    SpringApplication.run(ZipkinApplication.class, args);
}

}
