# Whitebird Gateway Service

### Spring Cloud Loadbalancer

目前在2.2.2的版本上已经可以选择使用`Spring Cloud Loadbalancer`了，但是有问题就是当去出了`Ribbon`模块或者将其引入之后，当再引入`Redis`的时候会提示`java.io.NotSerializableException: org.springframework.cloud.client.DefaultServiceInstance`的错误，所以导致目前不能使用


### Spring Cloud Circuit Breaker

当我使用`Resilience4J`的版本的时候，会无法生效

### 总结

在目前版本下，一些实验性质的负载均衡和断路由还是不太能替代Netflix的组件