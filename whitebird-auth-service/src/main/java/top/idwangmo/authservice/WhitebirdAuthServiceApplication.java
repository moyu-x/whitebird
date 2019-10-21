package top.idwangmo.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * whitebird auth service.
 *
 * @author idwangmo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
public class WhitebirdAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdAuthServiceApplication.class, args);
    }

}
