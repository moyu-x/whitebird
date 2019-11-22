# Whitebird

一个实验性质的项目，用于验证一些新技术

## 端口

| 服务名称                    | 端口号 |
| --------------------------- | ------ |
| whitebird-auth-server       | 11010  |
| whitebird-demo-server       | 11011  |
| whitebird-spring-boot-admin | 11012  |
| whitebird-account-service   | 11013  |
| whitebird-gateway-service   | 11014  |

## 还要实现的功能

-   [ ] Rsocket
-   [ ] Spring Cloud Stream 集成
-   [ ] EventSourcing 集成实例
-   [x] Auth Token 转化
-   [ ] Flowable 集成
-   [ ] 分布式调度
-   [ ] 自定义手机号码登录和扫码登录
-   [ ] 基于 Redis 的分布式锁的实现
-   [x] 限速
-   [ ] 集成阿里 OSS 服务
-   [ ] whitebird-account-service reactive 化
-   [ ] account 和 auth 的查询进入缓存
-   [ ] Spring Cloud Gateway 动态路由
-   [x] Docker 集成

## 技术选型

### Jib

在之前的的`Maven`中，我们会使用`docker-maven-plugin`插件，而在`Gradle`中，则会使用官方的`docker`插件，这样都避免不了要写`DockerFile`的问题，而使用`Jib`再保留的集成功能的情况下，而不用编写`Dockerfile`则是一种很好的体验，并且其编写配置也比较人性化

### 参考文章

1. [How to Live in a PostSpringCloutdNetflix World](https://www.slideshare.net/SpringCentral/how-to-live-in-a-postspringcloudnetflix-world)
