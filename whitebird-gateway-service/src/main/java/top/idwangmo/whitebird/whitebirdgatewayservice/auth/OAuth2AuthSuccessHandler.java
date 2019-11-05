package top.idwangmo.whitebird.whitebirdgatewayservice.auth;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;
import top.idwangmo.whitebird.commoncore.constant.SecurityConstants;
import top.idwangmo.whitebird.commoncore.model.WhitebirdUser;

import java.util.stream.Collectors;

/**
 * 认证成功处理.
 *
 * @author idwangmo
 */
public class OAuth2AuthSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        WhitebirdUser user = (WhitebirdUser) authentication.getPrincipal();
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;

        ServerHttpRequest serverHttpRequest = webFilterExchange.getExchange().getRequest()
                .mutate().headers(header -> {
                    header.add(SecurityConstants.USER_ID_HEADER, String.valueOf(user.getId()));
                    header.add(SecurityConstants.USER_HEADER, user.getUsername());
                    header.add(SecurityConstants.ROLE_HEADER,
                            authentication.getAuthorities().parallelStream()
                                    .map(GrantedAuthority::getAuthority).collect(
                                    Collectors.joining(",")));
                }).build();

        return webFilterExchange.getChain()
                .filter(webFilterExchange.getExchange().mutate().request(serverHttpRequest).build());
    }
}
