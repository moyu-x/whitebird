package top.idwangmo.whitebird.springbootadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring boot admin.
 *
 * @author idwangmo
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class WhitebirdSpringBootAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdSpringBootAdminServiceApplication.class, args);
    }

}
