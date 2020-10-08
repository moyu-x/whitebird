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

## 功能计划

这个参考Projects的[看板](https://github.com/idwangmo/whitebird/projects/1)

## 技术TIPs

### Jib

在之前的的`Maven`中，我们会使用`docker-maven-plugin`插件，而在`Gradle`中，则会使用官方的`docker`插件，这样都避免不了要写`DockerFile`的问题，而使用`Jib`再保留的集成功能的情况下，而不用编写`Dockerfile`则是一种很好的体验，并且其编写配置也比较人性化

### Lombok

Lombok 能解决代码一部分模板代码的问题，但是也会带来一些不可控的问题，所以我在这个项目中的只使用如下的一些注解:

```java
@Getter
@setter
@slf4j
@NoArgsConstructor
@AllArgsConstructor
```

### Gradle

如果需要在IDE中直接运行项目，需要设置Gradle的运行环境也是基于11的版本，不然回导致提示工具链报错的问题

## 参考

### 视频

1. [How to Live in a PostSpringCloutdNetflix World](https://www.slideshare.net/SpringCentral/how-to-live-in-a-postspringcloudnetflix-world)

### Snowflake 算法文章推荐

1. [理解分布式 id 生成算法 SnowFlake](https://segmentfault.com/a/1190000011282426)
2. [Leaf——美团点评分布式ID生成系统](https://tech.meituan.com/2017/04/21/mt-leaf.html)
3. [分布式唯一 id：snowflake 算法思考](https://juejin.im/post/5a7f9176f265da4e721c73a8)
4. [如何做一个靠谱的发号器](https://tech.youzan.com/id_gener)
5. [分布式唯一 ID 生成器](https://www.liaoxuefeng.com/article/1280526512029729)
