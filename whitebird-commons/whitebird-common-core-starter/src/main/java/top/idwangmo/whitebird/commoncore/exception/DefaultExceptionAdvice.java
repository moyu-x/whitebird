package top.idwangmo.whitebird.commoncore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.idwangmo.whitebird.commoncore.model.ExceptionBody;

import java.nio.file.AccessDeniedException;

/**
 * 通用异常处理.
 *
 * @author idwangmo
 */
@Slf4j
@ResponseBody
public class DefaultExceptionAdvice {

    /**
     * 错误返回统一包装.
     *
     * @param msg        错误信息
     * @param e          异常
     * @param httpStatus 状态码
     * @return ExceptionBody
     */
    private static ExceptionBody defHandler(String msg, Exception e, HttpStatus httpStatus) {
        log.error(msg, e);
        return ExceptionBody.builder().message(msg).code(httpStatus.value()).description(e.getMessage()).build();
    }

    /**
     * 返回状态码： 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ExceptionBody badRequestException(IllegalArgumentException e) {
        return defHandler("参数解析错误", e, HttpStatus.BAD_REQUEST);
    }

    /**
     * 返回状态码: 403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ExceptionBody accessDeniedException(AccessDeniedException e) {
        return defHandler("没有权限访问本系统", e, HttpStatus.FORBIDDEN);
    }

    /**
     * 返回状态码 405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ExceptionBody httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return defHandler("不支持当前方法", e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 返回状态码 415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ExceptionBody httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return defHandler("不支持的媒体格式", e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
