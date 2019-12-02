package top.idwangmo.whitebird.gatewayservice.handler;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * Swagger Resource Handler.
 *
 * @author idwangmo
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SwaggerResourceHandler implements HandlerFunction<ServerResponse> {

    private final @NonNull SwaggerResourcesProvider swaggerResourcesProvider;

    @Override
    public Mono<ServerResponse> handle(
        ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(swaggerResourcesProvider.get()));
    }
}
