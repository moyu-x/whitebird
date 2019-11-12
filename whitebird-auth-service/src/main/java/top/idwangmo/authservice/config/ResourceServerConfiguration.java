package top.idwangmo.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 作为资源服务器的配置信息.
 *
 * @author idwangmo
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/users/**")
                .and()
                .authorizeRequests()
                .antMatchers("/users/current").authenticated();
    }

}
