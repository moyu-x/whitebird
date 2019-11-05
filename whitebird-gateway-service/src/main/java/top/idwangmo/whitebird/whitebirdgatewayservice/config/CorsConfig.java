package top.idwangmo.whitebird.whitebirdgatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 跨域配置.
 *
 * @author idwangmo
 */
@Configuration
public class CorsConfig {

    private static final String ALL = "*";

    @Bean
    public CorsWebFilter corsWebFilter() {

        CorsConfiguration configuration = new CorsConfiguration();

        // cookie 跨域
        configuration.setAllowCredentials(Boolean.TRUE);
        configuration.addAllowedMethod(ALL);
        configuration.addAllowedOrigin(ALL);
        configuration.addAllowedHeader(ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", configuration);

        return new CorsWebFilter(source);
    }


}
