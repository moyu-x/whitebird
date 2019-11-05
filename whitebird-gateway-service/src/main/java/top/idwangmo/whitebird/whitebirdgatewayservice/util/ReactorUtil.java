package top.idwangmo.whitebird.whitebirdgatewayservice.util;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Reactor 的一些工具类.
 */
public class ReactorUtil {

    /**
     * 封装 gateway 一些出错的异常处理.
     *
     * @param exchange   ServerWebExchange
     * @param httpStatus HttpStatus
     * @return Void
     */
    public static Mono<Void> authErrorHandle(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer dataBuffer = dataBufferFactory.allocateBuffer();
        return response.writeWith(Mono.just(dataBuffer)).doOnError(error -> DataBufferUtils.release(dataBuffer));
    }
}
