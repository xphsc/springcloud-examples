## feign-client
## 概述

Feign是一个声明式的web服务client。
    它让编写web服务客户端更简单。
    使用Feign需要创建一个接口并在上面加注解。
    它有可插拔的注解支持，包括Feign的注解和JAX-RS的注解。
    Feign也支持可插拔式的编码器(encoder)和解码器(decoder)。
    Spring Cloud增加了对Spring MVC注解的支持，
    并且使用了Spring Web中默认使用的`HttpMessageConverters`。
    Spring Cloud整合Ribbon和Eureka，在使用Feign时提供负载均衡的http client。



### 如何引入Feign
   *pom.xml*

   ```
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-feign</artifactId>
   </dependency>
   ```
### 如何运行一个Feign client

```
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignApplication {
    public static void main(String[] args) {
    SpringApplication.run(FeignApplication.class, args);
}

}

```
## 配置详解
~~~~~
- 在@FeignClient注解中的值“user-server”
是一个任意client name,被用来创建一个Ribbon负载均衡器。
你也可以使用`url`属性来指定一个URL。
在application context中的bean名称是这个接口的全限定名。
你可以使用`@FeignClient`注解的`qualifier`
属性来指定你自己的别名。

- Spring Cloud Feign支持的一个重要概念是named client。
每个Feign client都是集合的一部分，
它们一起工作来连接远程服务.作为应用开发者，
你使用`@FeignClient`注解来给这个集合一个名字
。Spring Cloud使用`FeignClientsConfiguration`创建一个新的集合，
作为每个指定客户端的`ApplicationContext`。
这包括`feign.Decoder`,`feign.Encoder`,`feign.Contract`等。

-通过使用`@FeignClient`声明额外的配置
（在`FeignClientsConfiguration之`上），
Spring Cloud可让你完全控制Ribbon client。例如：

@FeignClient(name = "user-server", configuration = FooConfiguration.class)
public interface UserFeign {
    //..
}

在这种情况下，ribbon client由已经在FeignClientsConfiguration中的组件和FooConfiguration中的任何组件（后者将覆盖前者）组成。
~~~~~
**提示：**
`FooConfiguration`不需要`@Configuraion`注解。
(备注：这一点和ribbon client完全相反，
[`@RibbonClient`的configuration必须被`@Configuration`注解](http://cloud.spring.io/spring-cloud-static/Dalston.SR4/single/spring-cloud.html#_customizing_the_ribbon_client)。)
它不能在应用上下文被`@ComponentScan`扫描到，
否则它将被所有`@FeignClient`所共享。
(备注：在这个特性上，和`RibbonClient`一样)
`name`和`url`属性支持占位符,例如：
```
@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface UserFeign {
    //..
}
```
Spring Cloud Netflix默认提供以下bean (`BeanType` beanName：`ClassName`):

* `Decoder` feignDecoder: `ResponseEntityDecoder`(封装的`SpringDecoder`)
* `Encoder` feignEncoder: `SpringEncoder`
* `Logger` feignLogger: `Slf4jLogger`
* `Contract` feignContract: `SpringMvcContract`
* `Feign.Builder` feignBuilder: `HystrixFeign.Builder`
* `Client` feignClient: 如果ribbon开启是`LoadBalancerFeignClient`, 否则是默认的feign client。

通过设置`feign.okhttp.enabled`或`feign.httpclient.enabled`为`true`，可以使用`OkHttpClient`和`ApacheHttpClient`的feign client，并将它们放到classpath。

Spring Cloud Netflix默认情况下不提供以下bean，但仍从应用程序上下文中查找这些类型的bean以创建feign client：

* `Logger.Level`
* `Retryer`
* `ErrorDecoder`
* `Request.Options`
* `Collection<RequestInterceptor>`
* `SetterFactory`

创建这些类型的bean并将其放入@FeignClient配置
（例如上面的FooConfiguration）就能够覆盖所描述的每个bean。例如：

```
@Configuration
public class FooConfiguration {
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }


}
```
这用`feign.Contract.Default`代替了`SpringMvcContract`，
并且将一个`RequestInterceptor`添加到`RequestInterceptor`的集合中。

如果你更喜欢使用配置属性来配置所有`@FeignClient`，
则可以使用`default`这个feign名称来创建配置属性。
*application.yml*

```
feign:
  client:
    config:
      default:
        connectTimeout: 5000
        readTimeout: 5000
        loggerLevel: basic
```
**提示：** 如果你需要在你的`RequestInterceptor`中使用`ThreadLocal`域变量，
    你要么把Hystrix的`thread isolation strategy`设为`SEMAPHORE`，
    要么在Feign中禁用Hystrix。
*application.yml*

```
# To disable Hystrix in Feign
feign:
  hystrix:
    enabled: false

# To set thread isolation to SEMAPHORE
hystrix:
  command:
    default:
      execution:
        isolation:
          strategy: SEMAPHORE
```
## Feign的Hystrix支持
如果Hystrix在classpath上并且`feign.hystrix.enabled=true`,那么Feign将用一个断路器来包装所有的方法。返回一个`com.netflix.hystrix.HystrixCommand`也是可以的。这将让你使用响应式模式(调用`.toObservable()`或`.observe()`或异步使用（调用`.queue()`)
要基于每个client禁用Hystrix支持，需要创建一个具有“prototype”范围的`Feign.Builder`，例如：

```
@Configuration
public class FooConfiguration {
    @Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
```
**警告：**
 在Spring Cloud Dalston发布之前，
 如果Hystrix在classpath上
 (备注：pom.xml中有spring-cloud-starter-hystrix依赖)，
 Feign默认情况下会将所有方法封装在断路器中。
 Spring Cloud Dalston改变了这种默认行为，
 赞成采用选择加入的方式。
 (备注：Dalston前的版本中 feign.hystrix.enabled 默认值为true，Dalston及其之后的版本中 feign.hystrix.enabled 默认值为false)
  Feign Hystrix Fallbacks
 Hystrix支持fallback的概念：一个默认的代码路径，在断路或出现错误时执行。为给定的`@FeignClient`启用fallback功能，将`fallback`属性设置为实现fallback的类名称。你还需要将你的实现声明为Spring bean。

 ```
@FeignClient(value = "user-server" ,fallback = UserHystrixClientFallback.class)
public interface UserHystrixFeign {
    @RequestMapping(method = RequestMethod.GET, value ="/queryString")
    public String queryString();
}

public class UserHystrixClientFallback implements UserHystrixFeign {
    @Override
    public String queryString() {
        return "queryString方法故障，启用断路器";
    }
}

 ```
 如果需要访问fallback触发的原因，则可以使用`@FeignClient`中的`fallbackFactory`属性。

 ```
@FeignClient(value = "user-server" ,fallbackFactory = UserHystrixClientFallbackFactory.class)
public interface UserHystrixFeignFallbackFactory {
    @RequestMapping(method = RequestMethod.GET, value ="/queryString")
    public String queryString();
}

@Component
public  class UserHystrixClientFallbackFactory implements FallbackFactory<UserHystrixFeignFallbackFactory> {


    @Override
    public UserHystrixFeignFallbackFactory create(Throwable throwable) {
        return new UserHystrixFeignFallbackFactory() {
            @Override
            public String queryString() {
                return "queryString方法故障，启用断路器";
            }
        };
    }
}

 ```

 **警告：** Feign的fallback和Hystrix的fallback工作有一个限制。fallback当前不支持返回类型为`com.netflix.hystrix.HystrixCommand`和`rx.Observable`的方法。

 ## Feign和@Primary
 当使用Feign的Hystrix fallback时，ApplicationContext中有多个同一类型的Bean。这将会导致@Autowired不工作，因为没有一个确切的bean或者一个标记为primary的。要解决这个问题，Spring Cloud Netflix让所有的Feign实例为`@Primary`，所以Spring Framework将知道注入哪个bean。在一些情况下，这可能是不可取的。要关闭这个特性，设置`@FeignClient`的`primary`属性为`false`。

 ```
 @FeignClient(name = "user-server", primary = false)
 public interface UserFeign {
 	// methods here
 }
 ```

 ##  Feign的继承支持
 Feign通过单继承接口支持样板apis。这允许将通用操作分组为方便的基础接口。

 *UserService.java*

 ```
  @RequestMapping(method = RequestMethod.GET, value ="/queryById/{id}")
     Object queryById(@PathVariable("id") Integer id);
 ```
 *UserServiceImpl.java*

 ```
 @Service
 public class UserServiceImpl implements UserService {

 }
 ```
 UserExtendsFeign.java
 ```


@FeignClient("user-server")
public interface UserExtendsFeign extends UserService {


}
 ```

 **提示：**
 一般不建议在server和client之间共享一个接口。
 它引入了紧密的耦合，
 而且实际上以当前的形式用于Spring MVC并不起作用（
 方法参数映射不被继承）。

 ##  Feign请求响应的压缩
     你可以考虑为你的Feign请求开启请求或响应的GZIP压缩。
     你可以通过开启以下属性来完成此操作：

 ```
 feign.compression.request.enabled=true
 feign.compression.response.enabled=true
 ```
 Feign请求压缩为你提供了类似于设置Web服务器的设置：

 ```
 feign.compression.request.enabled=true
 feign.compression.request.mime-types=text/xml,application/xml,application/json
 feign.compression.request.min-request-size=2048
 ```
 这些属性允许你选择压缩的media type和最小请求阈值长度。

 ##  Feign日志
 为每一个Feign client创建一个logger，logger默认的名字是用来创建Feign client的接口的全限定类名。Feign的日志只响应`DEBUG`级别。

 *application.yml*

 `logging.level.project.user.UserClient: DEBUG`

 你可以为每一个client配置一个`Logger.Level`对象，告诉Feign去记录什么。有以下选择：

 * `NONE`, 不记录 (默认).

 * `BASIC`, 只记录请求方法、URL、响应状态码和执行时间。

 * `HEADERS`, 记录请求头和响应头的基本信息。

 * `FULL`, 记录请求和响应的headers、body和metadata。

 例如：以下将设置`Logger.Level`设为`FULL`:

 ```
 @Configuration
 public class FooConfiguration {
     @Bean
     Logger.Level feignLoggerLevel() {
         return Logger.Level.FULL;
     }
 }
 ```