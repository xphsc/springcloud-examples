# Spring RestTemplate as a Load Balancer Client 
[原文链接](http://cloud.spring.io/spring-cloud-static/Dalston.SR4/single/spring-cloud.html#_spring_resttemplate_as_a_load_balancer_client)

> 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。

```
@Configuration
public class RestTemplateConfig {
    @Bean
   @LoadBalanced
  public RestTemplate template(){
    return new RestTemplate();
}

}
```
**提示：** 从Spring Boot 1.4开始不再提供自动配置的RestTemplate Bean,你必须自己创建。

## Retrying Failed Requests
RestTemplatede的失败重试,默认是不可用的，如果需要开启，需要设置`spring.cloud.loadbalancer.retry.enabled=true`并且添加Spring Retry依赖。

```
<dependency>
	<groupId>org.springframework.retry</groupId>
	<artifactId>spring-retry</artifactId>
</dependency>
```
具有负载均衡功能的RestTemplate将遵循Ribbon关于重试的配置，如`client.ribbon.MaxAutoRetries`，`client.ribbon.MaxAutoRetriesNextServer`，`client.ribbon.OkToRetryOnAllOperations`。[Ribbon具体的配置](https://github.com/Netflix/ribbon/wiki/Getting-Started#the-properties-file-sample-clientproperties)。

# Multiple RestTemplate objects 
[原文链接](http://cloud.spring.io/spring-cloud-static/Dalston.SR4/single/spring-cloud.html#_multiple_resttemplate_objects)

如果需要同时使用具有负载均衡功能和普通的RestTemplate，可以如下配置：

```
@Configuration
public class RestTemplateConfig {
    @Bean
   @LoadBalanced
  public RestTemplate template(){
    return new RestTemplate();
}
   @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
@RestController
//@RibbonClient(name = "user-server", configuration = RibbonConfig.class)
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/queryString")
    public String queryString(){
       return restTemplate.getForObject("http://user-server/queryString",String.class);
    }

}
```
RestTemplate bean上的@Primary注解表明当@Autowired时没有特殊修饰符时使用该实例。 