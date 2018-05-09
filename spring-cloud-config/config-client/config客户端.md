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

> 配置刷新
> 在config clinet的pom.xml中新增spring-boot-starter-actuator监控模块，其中包含了/refresh刷新API。
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## 配置详解
~~~
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
~~~~
> spring.cloud.config.discovery.enabled参数设置为true，开启通过服务来访问Config Server的功能
> spring.cloud.config.discovery.serviceId参数来指定Config Server注册的服务名。
> spring.application.name和spring.cloud.config.profile 用来定位Git中的资源。