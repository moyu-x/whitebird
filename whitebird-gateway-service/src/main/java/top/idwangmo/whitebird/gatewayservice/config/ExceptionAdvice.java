package top.idwangmo.whitebird.gatewayservice.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.idwangmo.whitebird.commoncore.exception.DefaultExceptionAdvice;

/**
 * 异常处理.
 *
 * @author idwangmo
 */
@RestControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}
