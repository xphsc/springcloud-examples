package com.xphsc.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class MonitorApplication {
    public static void main(String[] args) {
    SpringApplication.run(MonitorApplication.class, args);
}

}
