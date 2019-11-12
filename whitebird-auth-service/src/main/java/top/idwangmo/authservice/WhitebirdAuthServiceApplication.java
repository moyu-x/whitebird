package top.idwangmo.authservice;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * whitebird auth service.
 *
 * @author idwangmo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableFeignClients(basePackages = "top.idwangmo.authservice.client")
public class WhitebirdAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdAuthServiceApplication.class, args);
    }


    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper;
    }
}
