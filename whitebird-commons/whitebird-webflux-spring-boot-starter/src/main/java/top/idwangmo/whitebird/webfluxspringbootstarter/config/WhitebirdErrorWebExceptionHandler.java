package top.idwangmo.whitebird.webfluxspringbootstarter.config;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 对 webflux 中的异常进行处理.
 *
 * @author idwangmo
 */
public class WhitebirdErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    private static Map<String, Integer> statusMap = new HashMap<>();

    static {
        statusMap.put("BadRequestException", 400);
    }

    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resourceProperties the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     */
    public WhitebirdErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                             ResourceProperties resourceProperties,
                                             ErrorProperties errorProperties,
                                             ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Throwable error = super.getError(request);
        Map<String, Object> errorAttributes = new HashMap<>(8);
        Optional<Integer> statusOptional = Optional.ofNullable(statusMap.get(error.getClass().getSimpleName()));

        errorAttributes.put("path", request.path());
        errorAttributes.put("status", statusOptional.orElse(500));
        errorAttributes.put("error", error.getClass().getSimpleName());
        errorAttributes.put("timestamp", LocalDateTime.now().toString());
        errorAttributes.put("message", error.getMessage());
        errorAttributes.put("requestId", request.exchange().getRequest().getId());
        return errorAttributes;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }
}
