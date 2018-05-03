package com.xphsc.ribbon.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@Configuration
public class RibbonConfig {

    @Bean
    public IPing ribbonPing(){
        return new PingUrl();
    }
}
