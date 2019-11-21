package top.idwangmo.whitebird.gatewayservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.idwangmo.whitebird.gatewayservice.filter.WhitebirdGatewayLogFilter;
import top.idwangmo.whitebird.gatewayservice.filter.WhitebirdRateLimitFilter;

/**
 * whitebird gateway service common configuration.
 *
 * @author idwangmo
 */
@Configuration
public class WhitebirdGatewayServiceConfiguration {

    @Bean(name = WhitebirdRateLimitFilter.BEAN_NAME)
    public WhitebirdRateLimitFilter whitebirdRateLimitFilter() {
        return new WhitebirdRateLimitFilter();
    }

    @Bean
    public WhitebirdGatewayLogFilter whitebirdGatewayLogFilter() {
        return new WhitebirdGatewayLogFilter();
    }

}

