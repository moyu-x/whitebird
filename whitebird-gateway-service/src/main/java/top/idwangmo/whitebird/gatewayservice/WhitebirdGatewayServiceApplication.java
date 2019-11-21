package top.idwangmo.whitebird.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.idwangmo.whitebird.gatewayservice.filter.WhitebirdGatewayLogFilter;
import top.idwangmo.whitebird.gatewayservice.filter.WhitebirdRateLimitFilter;

/**
 * whitebird gateway service.
 *
 * @author idwangmo
 */
@SpringBootApplication
public class WhitebirdGatewayServiceApplication {

    @Bean(name = WhitebirdRateLimitFilter.BEAN_NAME)
    public WhitebirdRateLimitFilter whitebirdRateLimitFilter() {
        return new WhitebirdRateLimitFilter();
    }

    @Bean
    public WhitebirdGatewayLogFilter whitebirdGatewayLogFilter() {
        return new WhitebirdGatewayLogFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(WhitebirdGatewayServiceApplication.class, args);
    }

}
