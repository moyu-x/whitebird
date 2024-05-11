package top.idwangmo.whitebird.demoservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器的配置.
 *
 * @author idwangmo
 */
@Configuration
@EnableResourceServer
@EnableMethodSecurity
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
            .requestMatchers(matchers -> matchers
                .requestMatchers("/**"))
            .authorizeRequests(requests -> requests
                .requestMatchers("/demos/current").authenticated()));
    }

}
