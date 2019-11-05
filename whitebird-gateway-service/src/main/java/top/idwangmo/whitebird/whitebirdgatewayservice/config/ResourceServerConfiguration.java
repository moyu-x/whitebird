package top.idwangmo.whitebird.whitebirdgatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import top.idwangmo.whitebird.commoncore.constant.SecurityConstants;
import top.idwangmo.whitebird.whitebirdgatewayservice.auth.OAuth2AuthSuccessHandler;
import top.idwangmo.whitebird.whitebirdgatewayservice.auth.WhiteAuthenticationManager;
import top.idwangmo.whitebird.whitebirdgatewayservice.auth.WhitebirdServerAccessDeniedHandler;
import top.idwangmo.whitebird.whitebirdgatewayservice.auth.WhitebirdServerAuthenticationEntryPoint;

/**
 * 资源服务器配置.
 *
 * @author idwangmo
 */
@Configuration
public class ResourceServerConfiguration {

    @Autowired
    private SecurityConstants securityConstants;

    @Autowired
    private TokenStore tokenStore;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // 认证处理器
        ReactiveAuthenticationManager reactiveAuthenticationManager = new WhiteAuthenticationManager(tokenStore);
        WhitebirdServerAuthenticationEntryPoint entryPoint = new WhitebirdServerAuthenticationEntryPoint();

        // token 转换器
        ServerBearerTokenAuthenticationConverter tokenAuthenticationConverter = new ServerBearerTokenAuthenticationConverter();
        tokenAuthenticationConverter.setAllowUriQueryParameter(true);

        // oauth2 认证过滤器
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(reactiveAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(tokenAuthenticationConverter);
        authenticationWebFilter
                .setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));
        authenticationWebFilter.setAuthenticationSuccessHandler(new OAuth2AuthSuccessHandler());
        http.addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchange = http.authorizeExchange();

        // @formatter:off
        authorizeExchange
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(new WhitebirdServerAccessDeniedHandler())
                        .authenticationEntryPoint(entryPoint)
                .and()
                    .headers()
                        .frameOptions()
                        .disable()
                .and()
                    .httpBasic().disable()
                    .csrf().disable();
        // @formatter:on

        return http.build();
    }

}
