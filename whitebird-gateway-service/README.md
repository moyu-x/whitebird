# Whitebird Gateway Service

## 组建选择原因

### Spring Cloud Loadbalancer

目前在 2.2.2 的版本上已经可以选择使用`Spring Cloud Loadbalancer`了，但是有问题就是当去出了`Ribbon`模块或者将其引入之后，当再引入`Redis`的时候会提示`java.io.NotSerializableException: org.springframework.cloud.client.DefaultServiceInstance`的错误，所以导致目前不能使用

### Spring Cloud Circuit Breaker

当我使用`Resilience4J`的版本的时候，会无法生效

### 总结

在目前版本下，一些实验性质的负载均衡和断路由还是不太能替代 Netflix 的组件

## Spring Cloud Filter Factory

在`Spring Cloud Gateway`本身就提供了许多已经实现好的工厂类，比如限流，修改请求数据和返回数据这些。

### 参考资料

1. [Spring Cloud Gateway-ServerWebExchange 核心方法与请求或者响应内容的修改](https://www.throwable.club/2019/05/18/spring-cloud-gateway-modify-request-response/#%E5%89%8D%E6%8F%90)
2. [gateway request size limit 1024B because netty default limit 1024,how to solve it? #581](https://github.com/spring-cloud/spring-cloud-gateway/issues/581)
3. [GatewaySampleApplication](https://github.com/spring-cloud/spring-cloud-gateway/blob/master/spring-cloud-gateway-sample/src/main/java/org/springframework/cloud/gateway/sample/GatewaySampleApplication.java)
4. [Spring Cloud（十三）：Spring Cloud Gateway（路由）](https://windmt.com/2018/05/07/spring-cloud-13-spring-cloud-gateway-router/)
