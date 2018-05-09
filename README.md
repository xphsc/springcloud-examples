## spring-cloud

## 概述
微服务是近期火爆IT业界的新概念，
  在某种意义上这算是一个全新架构，
  微服务继承了面向服务架构（SOA）的整体思路，
  强调将巨石型应用或服务拆分为由微小的服务应用。
  按照通常理解和定义，
  微服务是指开发一个单个小型的但有业务功能的服务，
  每个服务都有自己的处理和轻量通讯机制，
  可以部署在单个或多个服务器上。
  微服务也指一种种松耦合的、有一定的有界且有上下文的面向服务架构。
  在业务逻辑层面上，把集中整体的逻辑拆解为更细化的逻辑单元。在数据存储层面上，也可以按照情况从集中的存储拆解为更小的存储单元。所谓微服务架构，就是一种架构风格和设计模式，将应用分割为一系列细小的服务，每个服务专注于单一的功能，运行在独立的进程中，服务之间的边界清晰，采用轻量级通信机制相互沟通、配合来实现完整的应用，满足业务和用户的需求。微服务架构更多是属于应用技术架构。
本文主要介绍采用Spring Cloud框架平台来搭建微服务平台。Spring Cloud是Spring项目组的一个顶级项目，其主要内容是实现了基于JVM的云应用开发中的服务配置管理、服务注册、服务发现、断路器、智能路由、微代理、控制总线、全局锁、决策竞选、分布式会话和集群状态管理等功能。谈到Spring Cloud框架，就必须提及Spring Boot框架。因为Spring Cloud框架是基于Spring Boot框架的基础上搭建而成的。
   而Spring Boot 框架的设计目的就是简化基于Spring应用的初始搭建以及开发过程。

## **相关技术背景**


相关的技术背景包括：

Spring Boot框架是由Pivotal团队提供的全新框架，其设计目的是用来简化基于Spring应用的初始搭建以及开发过程。SpringBoot框架使用了特定的方式来进行应用系统的配置，从而使开发人员不再需要耗费大量精力去定义模板化的配置文件。Spring Cloud Starters是Spring Boot式的启动项目，也为Spring Cloud提供开箱即用的依赖管理。

Spring Cloud是一个基于Spring Boot实现的云应用开发工具，它为基于JVM的云应用开发中的配置管理、服务发现、断路器、智能路由、微代理、控制总线、全局锁、决策竞选、分布式会话和集群状态管理等操作提供了一种简单的开发方式。

Spring Cloud包含了多个子项目（针对分布式系统中涉及的多个不同开源产品），比如：Spring Cloud Config、Spring Cloud Netflix、Spring Cloud CloudFoundry 、Spring Cloud AWS、Spring Cloud Security、Spring Cloud Commons、Spring Cloud Zookeeper、Spring Cloud CLI等项目。

Spring Cloud Netflix，该项目是Spring Cloud的子项目之一，主要内容是对Netflix公司一系列开源产品的包装，它为Spring Boot应用提供了自配置的Netflix OSS整合。通过一些简单的注解，开发者就可以快速的在应用中配置一下常用模块并构建庞大的分布式系统。Spring Cloud Netflix主要提供的模块包括：服务发现（Eureka），断路器（Hystrix ），智能路有（Zuul），客户端负载均衡（Ribbon）等。Eureka（云端服务发现）是一个基于
REST 的服务，用于定位服务，以实现云端中间层服务发现和故障转移。Hystrix （熔断器）是容错管理工具，旨在通过熔断机制控制服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Zuul是在微服务平台上提供动态路由，监控，弹性，安全等边缘服务的框架。Zuul 相当于设备和 Netflix流应用的 Web 网站后端所有请求的前门。Ribbon提供云端负载均衡，有多种负载均衡策略可供选择，可配合服务发现和断路器使用。Turbine是聚合服务器发送事件流数据的一个工具，用来监控集群下hystrix
的metrics情况。Feign是一种声明式、模板化的HTTP客户端。

Spring Cloud Bus：事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与Spring Cloud Config联合实现热部署。

Spring Cloud Consul：封装了Consul操作，consul是一个服务发现与配置工具，与Docker容器可以无缝集成。与Eureka 服务具有同样的角色功能。

Spring Cloud for Cloud Foundry：通过Oauth2协议绑定服务到CloudFoundry，CloudFoundry是VMware 推出的开源PaaS云平台。

Spring Cloud Sleuth：日志收集工具包，封装了Dapper和log-based追踪以及Zipkin和HTrace操作，为SpringCloud 应用实现了一种分布式追踪解决方案。

Spring Cloud Data Flow：大数据操作工具，作为Spring XD的替代产品，它是一个混合计算模型，结合了流数据与批量数据的处理方式。

Spring Cloud Security：基于spring security的安全工具包，为你的应用程序添加安全控制。

Spring Cloud Zookeeper：操作Zookeeper的工具包，用于使用zookeeper方式的服务发现和配置管理。

Spring Cloud Stream：数据流操作开发包，封装了与Redis，Rabbit、Kafka等发送接收消息。

Spring Cloud Connectors：便于云端应用程序在各种PaaS平台连接到后端，如：数据库和消息代理服务。

Spring Cloud Cluster：提供Leadership选举，如：Zookeeper， Redis， Hazelcast ， Consul等常见状态模式的抽象和实现。

Docker 是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任意的机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口。在机器和数据中心中运行几乎没有性能开销。Docker不依赖于任何语言。

##  **微服务的核心问题**

在微服务架构设计中，需要抽象出一些核心问题来统一解决，这包括九条核心问题。第一点是服务的注册中心。第二是统一的接入服务接口。第三是服务的容错和负载均衡保证服务的高可用。第四是服务的限流和降级保证核心服务的稳定性。第五是服务系统的安全及全链路日志跟踪。第六是支持多种通讯方式。第七是监控和日志管理，第八是。下面详细描述各个核心问题的内容。

1.服务注册中心，主要实现服务注册与服务发现。微服务架构由一组独立的微服务组成，这些微服务之间存在一种发现机制，目前我们通过服务注册与发现来让微服务可以感知彼此，微服务框架在启动的时候，将自己的信息注册到注册中心，同时从注册中心订阅自己需要引用的服务。

![image](https://user-images.githubusercontent.com/11325103/26866789-58c502f2-4b95-11e7-80f0-eb42de8f4c16.png)


2. 统一的接入服务接口。由于把UI和后端服务分离。API Gateway是一个网关服务器，也可以说是进入系统的唯一节点。这跟面向对象设计模式中的Facade模式很像。API Gateway封装内部系统的架构，并且提供API给各个客户端。API Gateway还可能有其他功能，如授权、监控、负载均衡、缓存、请求分片和管理、静态响应处理等。

3. 服务的容错和负载均衡保证服务的高可用。为了保证微服务的高可用，每个微服务一般会有多个实例服务提供服务，此时需要客户端在进行服务的负载均衡；目前微服务框架中，现支持常见的负载均衡策略，如随机，轮训，hash，权重，连接数，缺省是根据连接数，连接数越少，优先级越高。

4.服务的容错可以保证核心服务的连续性。在调用服务集群的时候，如果一个微服务调用异常，如超时，或者发生连接异常，网络异常，则根据容错策略进行服务容错，如果连续失败多次，则需要直接熔断，不再发起调用，这样可以防止一个服务异常拖垮所有依赖它的服务。

5. 服务的限流和降级等措施可以保证核心服务的稳定性。随着访问量的增加，微服务架构设置了一个系统能够处理的极限阀值，超过这个阀值的请求则直接拒绝。同时，为了保证某些核心服务可用，需要对某些非核心业务进行降级。这些操作可以自动降级也可以人工降级。

6.安全控制和权限验证：保障系统的安全。微服务架构推荐采用token机制保证微服务的安全。用户在登录的时候会颁发给用户一个token，用户每次访问平台的时候，需要传递此token，后台校验此token 的合法性，如果合法则可以访问，如果没有token或者token不合法，则禁止访问。

7.支持多种通讯方式。在微服务架构下，一般采用轻量级的方式进行通讯，每个微服务都统一对外暴露RESTFul服务，无论是前端调用后端服务，还是后端服务间的调用，都统一走RESTFul，这样统一了协议栈。

8. 日志和监控管理。在微服务框架中，所有的系统调用边界、请求接入接出边界，都存在统一的日志埋点，这些日志埋点和监控系统对接，可以方便的查看系统的运行各项指标，同时也可以根据日志跟踪到一个服务从前到后的整个调用链路。

9.统一配置管理。统一的配置服务器为各应用的所有环境提供了一个中心化的外部配置。这样可以简化很多开发过程中的繁琐工作。

10.CI/CD自动化。快速完成源码构建、镜像打包、应用部署，实现微服务的高效运营。

