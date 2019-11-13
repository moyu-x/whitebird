package top.idwangmo.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * whitebird auth service.
 *
 * @author idwangmo
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "top.idwangmo.authservice.client")
public class WhitebirdAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdAuthServiceApplication.class, args);
    }

}
