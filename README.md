# Whitebird

一个实验性质的项目，用于验证一些新技术

## 端口

| 服务名称                            | 端口号 |
| ----------------------------------- | ------ |
| whitebird-auth-service              | 11010  |
| whitebird-demo-service              | 11011  |
| whitebird-config-service            | 11012  |
| whitebird-account-service           | 11013  |
| whitebird-gateway-service           | 11014  |
| whitebird-spring-boot-admin-service | 10015  |

## 项目结构

```
.
├── gradle
├── whitebird-account-service
├── whitebird-auth-service
├── whitebird-commons
│   ├── whitebird-core-spring-boot-starter
│   ├── whitebird-jpa-spring-boot-starter
│   ├── whitebird-oauth2-spring-boot-starter
│   ├── whitebird-redis-spring-boot-starer
│   ├── whitebird-swagger-spring-boot-starter
│   └── whitebird-web-spring-boot-starter
├── whitebird-config-folder
├── whitebird-config-service
├── whitebird-demo-service
├── whitebird-deploy-file
├── whitebird-gateway-service
└── whitebird-spring-boot-admin-service
```

## 还要实现的功能

- [ ] Rsocket
- [ ] Spring Cloud Stream 集成
- [ ] EventSourcing 集成实例
- [x] Auth Token 转化
- [ ] Flowable 集成
- [ ] 分布式调度
- [ ] 自定义手机号码登录和扫码登录
- [ ] 基于 Redis 的分布式锁的实现
- [x] 限速
- [ ] 集成阿里 OSS 服务
- [ ] whitebird-account-service reactive 化
- [ ] account 和 auth 的查询进入缓存
- [ ] Spring Cloud Gateway 动态路由
- [x] Docker 集成

## 技术

### Jib

在之前的的`Maven`中，我们会使用`docker-maven-plugin`插件，而在`Gradle`中，则会使用官方的`docker`插件，这样都避免不了要写`DockerFile`的问题，而使用`Jib`再保留的集成功能的情况下，而不用编写`Dockerfile`则是一种很好的体验，并且其编写配置也比较人性化

### Lombok

Lombok 能解决代码一部分模板代码的问题，但是也会带来一些不可控的问题，所以我在这个项目中的只使用如下的一些注解:

```
@Getter
@setter
@slf4j
@NoArgsConstructor
@AllArgsConstructor
```

### 参考文章

1. [How to Live in a PostSpringCloutdNetflix World](https://www.slideshare.net/SpringCentral/how-to-live-in-a-postspringcloudnetflix-world)
