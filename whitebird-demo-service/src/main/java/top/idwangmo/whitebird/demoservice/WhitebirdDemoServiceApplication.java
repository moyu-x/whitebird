package top.idwangmo.whitebird.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

/**
 * 演示测试使用的服务.
 *
 * @author idwangmo
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableOAuth2Sso
public class WhitebirdDemoServiceApplication {

    @Primary
    @Bean
    public OAuth2ClientContext singletonClientContext() {
        return new DefaultOAuth2ClientContext();
    }

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdDemoServiceApplication.class, args);
    }

}
