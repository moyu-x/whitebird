package top.idwangmo.whitebird.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.idwangmo.whitebird.commoncore.constant.ClientConstant;

/**
 * whitebird auth service.
 *
 * @author idwangmo
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = ClientConstant.AUTH_CLIENT)
public class WhitebirdAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdAuthServiceApplication.class, args);
    }

}
