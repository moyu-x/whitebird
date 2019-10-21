package top.idwangmo.whitebird.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 演示测试使用的服务.
 *
 * @author idwangmo
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableOAuth2Sso
public class WhitebirdDemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdDemoServiceApplication.class, args);
    }

}
