package top.idwangmo.whitebird.gatewayservice.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import top.idwangmo.whitebird.gatewayservice.handler.SwaggerResourceHandler;

/**
 * 路由配置信息.
 *
 * @author idwangmo
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RouterFunctionConfiguration {

    private final @NonNull SwaggerResourceHandler swaggerResourceHandler;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions
            .route(RequestPredicates.GET("/swaggre-resources").and(RequestPredicates.accept(MediaType.ALL)),
                swaggerResourceHandler);
    }

}
