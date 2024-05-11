package top.idwangmo.whitebird.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * web 安全配置.
 *
 * @author idwangmo
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 在 Spring boot 2 或者 Spring Security 5 中一定要设置.
     *
     * @return Authentication
     * @throws Exception exception
     */
    /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf(csrf -> csrf
            .requestMatchers(matchers -> matchers.requestMatchers("/oauth/**", "/login/**", "/logout/**", "/users/registry"))
            .authorizeRequests(requests -> requests
                .requestMatchers("/oauth/**").authenticated())
            .formLogin(login -> login.permitAll()));
        // @formatter:on
    }
}
