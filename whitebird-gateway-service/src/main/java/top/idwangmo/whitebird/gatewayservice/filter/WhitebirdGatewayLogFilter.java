package top.idwangmo.whitebird.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

/**
 * 在 gateway 中记录日志.
 *
 * @author idwangmo
 */
@Slf4j
public class WhitebirdGatewayLogFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        return chain.filter(exchange).then(
            Mono.fromRunnable(() -> requestInfoLog(exchange))
        );
    }

    private void requestInfoLog(ServerWebExchange exchange) {
        URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        HashSet<URI> originUris = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = Optional.ofNullable(originUris).orElse(new HashSet<>()).stream().findFirst().orElse(null);

        if (Objects.nonNull(uri) && Objects.nonNull(route) && Objects.nonNull(originUri)) {
            log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                route.getId(), uri.getScheme(), uri.getAuthority(), uri.getPath(), LocalDateTime.now()
            );
        }
    }
}
