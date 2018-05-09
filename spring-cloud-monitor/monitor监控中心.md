## spring-cloud-monitor
## 概述
 ``` monitor 监控中心
 ```
## 如何引入 monitor server
```
    <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-server</artifactId>
            <version>1.5.7</version>
        </dependency>
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-server-ui</artifactId>
            <version>1.5.7</version>
        </dependency>
```

## 开启监控
```
添加 @EnableAdminServer 注解开启监控

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class MonitorApplication {
    public static void main(String[] args) {
    SpringApplication.run(MonitorApplication.class, args);
}

}
```

## Client 端

添加依赖
pom.xml
```
 <dependency>
   <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client
    </artifactId>
     <version>1.3.4</version>
      </dependency>
这里的 spring-boot-admin-starter-client 会自动依赖 jolokia-core，jolokia是用于 JMX-bean 管理的
```

## 触发自动配置、指明 Server 注册地址
application.properties
```
spring.boot.admin.url=http://localhost:8769
 Server 端我们使用默认的 8768 端口，所以这里声明 Server 的地址为：http://localhost:8080
```
## 开始管理
```
至此，启动 Server 端和 Client 端，在浏览器输入 Server 的地址：http://localhost:8080 就能看到“注册”进来的 Spring Boot 应用信息了。
```
## 显示应用版本
```
为了在 Spring Boot Admin 的应用管理列表显示被管理应用的版本号，你需要设置 info.version，例如使用 maven filtering：
application.properties
 info.version=@project.version@
这里设置显示的版本号为 Maven pom.xml 中的构建版本号。
```
# JMX-bean管理
```
JMX-bean 管理需要使用第三方的 jolokia ，因为 spring-boot-admin-starter-client 会自动依赖 jolokia-core，所以这里不需要显示依赖了，第三节的基于 Eureka 注册发现的配置中，就需要显示地依赖：

pom.xml
<dependency>
   <groupId>org.jolokia</groupId>
      <artifactId>jolokia-core</artifactId>
      </dependency>
```
## Loglevel 管理
```
当前日志级别管理仅限 Logback，通过 JMX 实现，所以需要依赖 jolokia 。同时，还需要配置 Logback 的 JMXConfigurator：
logback.xml

 <configuration>
   <include resource="org/springframework/boot/logging/logback/base.xml"/>
    <jmxConfigurator/>
    </configuration>
这个 logback.xml 放在与 application.properties 同级的目录就可以了，如果不配置 Logback，那么 Spring Boot Admin 就无法管理应用的日志级别。
```
## Server 端监控自己
```
以上的配置，基本就可以很好工作了。
但是有一个问题，我们没有监控作为 Server 端的 Spring Boot Admin 自身。如果要监控到 Server 自己，把 Server 端也当作是 Client 一样来配置就可以实现了：把 2.2.1、2.2.2、2.4、2.6 的步骤在 Server 端也配置一遍。
```
