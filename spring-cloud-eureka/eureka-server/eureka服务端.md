## eureka-service
## 概述



### 如何引入Eureka Server

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka-server</artifactId>
</dependency>
```
### 如何运行一个Eureka Server

```
@SpringBootApplication
@EnableEurekaServer
public class ServicerEurekaApplication {
    public static void main(String[] args) {
    SpringApplication.run(ServicerEurekaApplication.class, args);
}

}
```
Eureka Server有一个UI主页来查看注册的服务信息，```/eureka/```。

### Standalone Mode
在单机模式下，更喜欢关闭client端的行为，如`registerWithEureka`，`fetchRegistry`，所以它不会试图去到达它的peers。

*aplication.yml (Standalone Eureka Server)*

```
server:
  port: 8761

eureka:
  instance:
    hostname: xphsc
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
注意`serverUrl`指向本地实例的host。
   配置主机名 C:\Windows\System32\drivers\etc\hosts
            127.0.0.1 xphsc
