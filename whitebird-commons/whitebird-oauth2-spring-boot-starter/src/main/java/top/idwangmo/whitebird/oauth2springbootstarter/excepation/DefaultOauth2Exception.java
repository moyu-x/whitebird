package top.idwangmo.whitebird.oauth2springbootstarter.excepation;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.idwangmo.whitebird.commoncore.model.ExceptionResponse;

import static top.idwangmo.whitebird.commoncore.exception.DefaultExceptionAdvice.defHandler;

/**
 * oauth2 常见异常封装.
 *
 * @author idwangmo
 */
public class DefaultOauth2Exception {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UsernameNotFoundException.class})
    public ExceptionResponse unauthorized(UsernameNotFoundException e) {
        return defHandler(e.getMessage(), e, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 返回状态码: 403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ExceptionResponse accessDeniedException(AccessDeniedException e) {
        return defHandler("没有权限访问本系统", e, HttpStatus.FORBIDDEN);
    }

}
