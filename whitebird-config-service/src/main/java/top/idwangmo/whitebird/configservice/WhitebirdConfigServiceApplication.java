package top.idwangmo.whitebird.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * whitebird config service.
 *
 * @author idwangmo
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class WhitebirdConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdConfigServiceApplication.class, args);
    }

}
