## spring-cloud-zipkin
## 概述
 ```Spring Cloud Sleuth为Spring Cloud提供了分布式追踪方案，
 借用了Dapper，Zipkin和HTrace。
 对于大多数用户来说Sleuth应该是看不见的，与外部系统的相互作用是自动的。
 您可以简单地在日志中捕获数据，或将数据发送到远程收集服务。

    For full documentation visit [spring cloud Sleuth](http://cloud.spring.io/spring-cloud-sleuth/).
 ```
### 如何引入zipkin Server
```
<dependencyManagement> (1)
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Brixton.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependency> (2)
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-stream</artifactId>
</dependency>
<dependency> (3)
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<!-- EXAMPLE FOR RABBIT BINDING -->
<dependency> (4)
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
</dependency>
```
1. 为了不手动添加版本号，更好的方式是通过Spring BOM添加dependencymanagement
2. 添加依赖到`spring-cloud-sleuth-stream`
3. 添加依赖到`spring-cloud-starter-sleuth`
4. 添加一个binder(e.g.Rabbit binder)来告诉Spring Cloud Stream应该绑定什么

### 如何运行一个zipkin Server
  @EnableZipkinStreamServer注解
```
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinStreamServer
public class ZipkinApplication {
    public static void main(String[] args) {
    SpringApplication.run(ZipkinApplication.class, args);
}

}
```
> @EnableZipkinStreamServer也使用@EnableZipkinServer注解，因此进程也会显示标准Zipkin服务终端以通过HTTP收集span，且可以通过Zipkin Web UI查询