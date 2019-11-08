package top.idwangmo.whitebird.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * whitebird 用户管理服务.
 *
 * @author idwangmo
 */
@EnableDiscoveryClient
@SpringBootApplication
public class WhitebirdAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdAccountServiceApplication.class, args);
    }

}
