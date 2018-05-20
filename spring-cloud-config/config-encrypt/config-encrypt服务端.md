## config-encrypt
## 概述

从配置获取的配置默认是明文的，有些像数据源这样的配置需要加密的话，需要对配置中心进行加密处理。

下面使用对称性加密来加密配置，需要配置一个密钥，当然也可以使用RSA非对称性加密，但对称加密比较方便也够用了，这里就以对称加密来配置即可。

### 如何引入config encrypt server

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
##
~~~
spring:
 profiles:
     active: native   //读取本地配置文件
 cloud:
    config:
      server:
        native:
          search-locations: classpath:/configPro  # 自动抓取src/main/resources/configPro/文件夹下的配置

~~~
## 安装JCE

JDK下的JCR默认是有长度限制的，需要替换没有长度限制的JCE版本。
> 把下载包里面的两个jar文件复制替换到JAVA_HOME/jre/lib/security目录下。
##  添加加密KEY
配置中心配置文件中加入加密密钥。
encrypt:
  key: 0e010e17-2529-4581-b907-c8edcfd6be09

## 查看加密功能状态

http://localhost:8888/encrypt/status
> 功能正常会显示OK
 {"status":"OK"}

## 加密解密
~~~
对 client字符串加密
curl http://localhost:8888/encrypt -d client
对 client字符串解密
curl http:// localhost:8888/decrypt -d  6c5f244255e335947b5383e51344150c69aba8c0bce0c0d86fb29716bb4c4601
~~~
## 配置文件
~~~
config:
  client:  '{cipher}6c5f244255e335947b5383e51344150c69aba8c0bce0c0d86fb29716bb4c4601'
~~~
>需要加密的内容以{cipher}开头，并注意要使节单引号包起来，不然报错。

## 读取配置
~~~
这样客户端读取出来的配置是自动解密了的，如果要关闭自动解密功能通过客户端自己来解密，同时也要保留加解密的端点可以通过关闭以下配置即可。
~~~
>spring.cloud.config.server.encrypt.enabled=false