## config-client
## 概述



### config client

```
  <dependency>
             <groupId>org.springframework.cloud</groupId>
             <artifactId>spring-cloud-config-client</artifactId>
         </dependency>
```
### 如何运行一个config client

```
@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClientApplication.class).run(args);
    }
}

```
## 配置详解
~~~~~
spring:
#  cloud:
#    config:
#      profile: dev
#      discovery:
#        enabled: true
#        serviceId: config-server
   cloud:
      config:
        uri: http://xphsc:8888
        profile: dev