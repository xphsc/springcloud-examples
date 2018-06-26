package com.xphsc.refresh;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigRefreshServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigRefreshServerApplication.class).run(args);
    }
}
