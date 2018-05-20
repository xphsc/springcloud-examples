package com.xphsc.encrypt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigEncryptServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigEncryptServerApplication.class).run(args);
    }
}
