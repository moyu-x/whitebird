package top.idwangmo.whitebird.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WhitebirdDemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdDemoServiceApplication.class, args);
    }

}
