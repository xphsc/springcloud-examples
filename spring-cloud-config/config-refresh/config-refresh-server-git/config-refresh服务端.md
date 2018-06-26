## config-git-server
## 概述



### 如何引入config git server

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
### 如何运行一个config git server

```
@EnableConfigServer
@SpringBootApplication
public class ConfigGitServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigGitServerApplication.class).run(args);
    }
}

```

## git配置详解
~~~
spring.cloud.config.server.git.uri=
spring.cloud.config.server.git.searchPaths=
spring.cloud.config.server.git.username=username
spring.cloud.config.server.git.password=password
~~~
> spring.cloud.config.server.git.uri：配置git仓库位置
> spring.cloud.config.server.git.searchPaths：配置仓库路径下的相对搜索位置，可以配置多个
> spring.cloud.config.server.git.username：访问git仓库的用户名
> spring.cloud.config.server.git.password：访问git仓库的用户密码
> URL与配置文件的映射关系如下：
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
> 上面的url会映射{application}-{profile}.properties对应的配置文件，{label}对应git上不同的分支，默认为master。



