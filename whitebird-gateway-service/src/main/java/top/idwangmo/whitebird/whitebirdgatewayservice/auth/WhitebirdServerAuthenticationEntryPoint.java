package top.idwangmo.whitebird.whitebirdgatewayservice.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.idwangmo.whitebird.whitebirdgatewayservice.util.ReactorUtil;

/**
 * 401 未授权处理.
 *
 * @author idwangmo
 */
public class WhitebirdServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return ReactorUtil.authErrorHandle(exchange, HttpStatus.UNAUTHORIZED);
    }
}
