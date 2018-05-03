package com.xphsc.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@Configuration
public class RestTemplateConfig {
    @Bean
   @LoadBalanced
  public RestTemplate template(){
    return new RestTemplate();
}

}
