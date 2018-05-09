## config-server
## 概述



### 如何引入config local server

```
 <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
 <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka-server</artifactId>
    </dependency>
```


### 如何运行一个config Server

```
@EnableConfigServer
@SpringBootApplication
public class ConfiglocalServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfiglocalServerApplication.class).run(args);
    }
}
```
## 配置详解
~~~~~
spring:
 profiles:
     active: native   //读取本地配置文件
 cloud:
    config:
      server:
        native:
          search-locations: classpath:/configPro  # 自动抓取src/main/resources/configPro/文件夹下的配置