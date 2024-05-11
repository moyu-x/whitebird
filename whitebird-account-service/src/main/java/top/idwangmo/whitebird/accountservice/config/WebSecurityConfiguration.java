package top.idwangmo.whitebird.accountservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * web security configuration.
 *
 * @author idwangmo
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.authorizeRequests(requests -> requests.requestMatchers("/users/**").permitAll()));
        return http.build();
    }
}
