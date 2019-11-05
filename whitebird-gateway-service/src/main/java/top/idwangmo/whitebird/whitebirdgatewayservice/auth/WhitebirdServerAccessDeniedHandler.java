package top.idwangmo.whitebird.whitebirdgatewayservice.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.idwangmo.whitebird.whitebirdgatewayservice.util.ReactorUtil;

/**
 * 处理 403.
 *
 * @author idwangmo
 */
public class WhitebirdServerAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        return ReactorUtil.authErrorHandle(exchange, HttpStatus.FORBIDDEN);
    }
}
